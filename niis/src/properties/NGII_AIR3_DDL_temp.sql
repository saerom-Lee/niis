
  CREATE MATERIALIZED VIEW AIR_INSPMONTH_STS (QRT, INT_V_CNT, WEB_V_CNT, INT_RD_CNT, WEB_RD_CNT, INT_R_CNT, WEB_R_CNT)
  ORGANIZATION HEAP PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE NGII_AIR 
  BUILD IMMEDIATE
  USING INDEX 
  REFRESH COMPLETE ON DEMAND START WITH sysdate+0 NEXT TRUNC(SYSDATE) + 1 + 1/24 + 5/(24*60)
  USING DEFAULT LOCAL ROLLBACK SEGMENT
  USING ENFORCED CONSTRAINTS DISABLE QUERY REWRITE
  AS SELECT T.QRT,
       NVL (INT_V_CNT, 0) INT_V_CNT,
       NVL (WEB_V_CNT, 0) WEB_V_CNT,
       NVL (INT_RD_CNT, 0) INT_RD_CNT,
       NVL (WEB_RD_CNT, 0) WEB_RD_CNT,
       NVL (INT_R_CNT, 0) INT_R_CNT,
       NVL (WEB_R_CNT, 0) WEB_R_CNT
  FROM (    SELECT TO_CHAR (
                      ADD_MONTHS (
                         TO_DATE (TO_CHAR (SYSDATE, 'YYYY') || '12', 'YYYYMM'),
                         -LEVEL + 1),
                      'YYYYMM')
                      QRT
              FROM DUAL
        CONNECT BY LEVEL <= (TO_CHAR (SYSDATE, 'YYYY') - 2010 + 1) * 12) T,
       (                                                       /* 인트라넷 방문자수 */
        SELECT   TO_CHAR (ACS_CRDT, 'YYYYMM') QRT, COUNT (*) INT_V_CNT
            FROM NGIISMART.ITR_USR_LOG
        GROUP BY TO_CHAR (ACS_CRDT, 'YYYYMM')) INT_V,
       (                                                         /*인트라넷 열람자수*/
        SELECT   QRT, COUNT (*) INT_RD_CNT
            FROM (  SELECT RQESTER_ID,
                           TO_CHAR (TO_DATE (OCCRRNC_DE, 'YYYYMMDD'), 'YYYYMM')
                              QRT,
                           COUNT (*) INT_RD_CNT
                      FROM NGIISMART.COMTNWEBLOG
                     WHERE     URL = '/iim/airImg/proxy.do'
                           AND SUBSTR (OCCRRNC_DE, 1, 4) BETWEEN 2010
                                                             AND TO_CHAR (
                                                                    SYSDATE,
                                                                    'YYYY')
                  GROUP BY RQESTER_ID,
                           URL,
                           TO_CHAR (TO_DATE (OCCRRNC_DE, 'YYYYMMDD'), 'YYYYMM'))
        GROUP BY QRT) INT_RD,
       (                                                         /*인트라넷 열람매수*/
        SELECT   TO_CHAR (TO_DATE (OCCRRNC_DE, 'YYYYMMDD'), 'YYYYMM') QRT,
                 COUNT (*) INT_R_CNT
            FROM NGIISMART.COMTNWEBLOG
           WHERE     URL = '/iim/airImg/proxy.do'
                 AND SUBSTR (OCCRRNC_DE, 1, 4) BETWEEN 2010
                                                   AND TO_CHAR (SYSDATE,
                                                                'YYYY')
        GROUP BY URL, TO_CHAR (TO_DATE (OCCRRNC_DE, 'YYYYMMDD'), 'YYYYMM')) INT_R,
       (                                                          /*인터넷 방문횟수*/
        SELECT   TO_CHAR (LOG_YMD, 'YYYYMM') QRT, COUNT (*) WEB_V_CNT
            FROM WEB_LOGIN_LOG@DL_NGIWEB
           WHERE TO_CHAR (LOG_YMD, 'YYYY') BETWEEN 2010
                                               AND TO_CHAR (SYSDATE, 'YYYY')
        GROUP BY TO_CHAR (LOG_YMD, 'YYYYMM')) WEB_V,
       (                                                          /*인터넷 열람자수*/
        SELECT   QRT, COUNT (*) WEB_RD_CNT
            FROM (  SELECT TO_CHAR (LOG_YMD, 'YYYYMM') QRT,
                           USER_ID,
                           COUNT (*) CNT
                      FROM WEB_IMGVIEW_LOG@DL_NGIWEB
                     WHERE     TO_CHAR (LOG_YMD, 'YYYY') BETWEEN 2010
                                                             AND TO_CHAR (
                                                                    SYSDATE,
                                                                    'YYYY')
                           AND IMG_CLS = 'PDT001'
                  GROUP BY TO_CHAR (LOG_YMD, 'YYYYMM'), USER_ID)
        GROUP BY QRT) WEB_RD,
       (                                                          /*인터넷 열람매수*/
        SELECT   TO_CHAR (LOG_YMD, 'YYYYMM') QRT, COUNT (*) WEB_R_CNT
            FROM WEB_IMGVIEW_LOG@DL_NGIWEB
           WHERE     TO_CHAR (LOG_YMD, 'YYYY') BETWEEN 2010
                                                   AND TO_CHAR (SYSDATE,
                                                                'YYYY')
                 AND IMG_CLS = 'PDT001'
        GROUP BY TO_CHAR (LOG_YMD, 'YYYYMM')) WEB_R
 WHERE     T.QRT = INT_V.QRT(+)
       AND T.QRT = INT_RD.QRT(+)
       AND T.QRT = INT_R.QRT(+)
       AND T.QRT = WEB_V.QRT(+)
       AND T.QRT = WEB_RD.QRT(+)
       AND T.QRT = WEB_R.QRT(+);

   COMMENT ON MATERIALIZED VIEW AIR_INSPMONTH_STS  IS '월별 항공영상';
