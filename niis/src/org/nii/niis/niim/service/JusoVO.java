package org.nii.niis.niim.service;

import java.io.Serializable;

import com.vividsolutions.jts.geom.Geometry;

@SuppressWarnings("serial")
public class JusoVO implements Serializable {
	private String  ALWNC_DE			="";
	private String  ALWNC_M_CD			="";
	private String  ALWNC_RESN			="";
	private String  ALWNC_R_CD			="";
	private String  BSI_INT				="";
	private int		CRSRD_CNT			=0;
	private String  ENG_RN				="";
	private String  INPUT_MTHD			="";
	private String  ISSU_YN				="";
	private String  MVMN_DE				="";
	private String  MVMN_RESN			="";
	private String  MVM_RES_CD			="";
	private String  NLR_LCL_NO			="";
	private String  NTFC_DE				="";
	private String  OPERT_DE			="";
	private String  OPE_MAN_ID			="";
	private int  	PAR_RDS_NO			=0;
	private String  PAR_SIG_CD			="";
	private String  RBP_CN				="";
	private String  RDS_DPN_SE			="";
	private int  	RDS_MAN_NO			=0;
	private String  REP_CN				="";
	private String  RN					="";
	private String  RNCHA_R_CD			="";
	private String  RN_CD				="";
	private String  RN_DLB_DE			="";
	private int  	ROAD_BT				=0;
	private int  	ROAD_LT				=0;
	private int  	ROAD_PY_LT			=0;
	private String  ROA_CLS_SE			="";
	private String  ROA_MAN_ES			="";
	private String  SIG_CD				="";
	private String  WDR_RD_CD			="";
	private String  POINT_X				= "";
	private String  POINT_Y				= "";
	
	private byte[]  wkb_geometry = null;

	Geometry geom = null;
	
	public byte[] getWkb_geometry() {
		return wkb_geometry;
	}

	public void setWkb_geometry(byte[] wkb_geometry) {
		this.wkb_geometry = wkb_geometry;
	}

	public Geometry getGeom() {
		return geom;
	}

	public void setGeom(Geometry geom) {
		this.geom = geom;
	}


	public String getPOINT_X() {
		return POINT_X;
	}

	public void setPOINT_X(String pOINT_X) {
		POINT_X = pOINT_X;
	}

	public String getPOINT_Y() {
		return POINT_Y;
	}

	public void setPOINT_Y(String pOINT_Y) {
		POINT_Y = pOINT_Y;
	}

	public String getALWNC_DE() {
		return ALWNC_DE;
	}

	public void setALWNC_DE(String aLWNC_DE) {
		ALWNC_DE = aLWNC_DE;
	}

	public String getALWNC_M_CD() {
		return ALWNC_M_CD;
	}

	public void setALWNC_M_CD(String aLWNC_M_CD) {
		ALWNC_M_CD = aLWNC_M_CD;
	}

	public String getALWNC_RESN() {
		return ALWNC_RESN;
	}

	public void setALWNC_RESN(String aLWNC_RESN) {
		ALWNC_RESN = aLWNC_RESN;
	}

	public String getALWNC_R_CD() {
		return ALWNC_R_CD;
	}

	public void setALWNC_R_CD(String aLWNC_R_CD) {
		ALWNC_R_CD = aLWNC_R_CD;
	}

	public String getBSI_INT() {
		return BSI_INT;
	}

	public void setBSI_INT(String bSI_INT) {
		BSI_INT = bSI_INT;
	}

	public int getCRSRD_CNT() {
		return CRSRD_CNT;
	}

	public void setCRSRD_CNT(int cRSRD_CNT) {
		CRSRD_CNT = cRSRD_CNT;
	}

	public String getENG_RN() {
		return ENG_RN;
	}

	public void setENG_RN(String eNG_RN) {
		ENG_RN = eNG_RN;
	}

	public String getINPUT_MTHD() {
		return INPUT_MTHD;
	}

	public void setINPUT_MTHD(String iNPUT_MTHD) {
		INPUT_MTHD = iNPUT_MTHD;
	}

	public String getISSU_YN() {
		return ISSU_YN;
	}

	public void setISSU_YN(String iSSU_YN) {
		ISSU_YN = iSSU_YN;
	}

	public String getMVMN_DE() {
		return MVMN_DE;
	}

	public void setMVMN_DE(String mVMN_DE) {
		MVMN_DE = mVMN_DE;
	}

	public String getMVMN_RESN() {
		return MVMN_RESN;
	}

	public void setMVMN_RESN(String mVMN_RESN) {
		MVMN_RESN = mVMN_RESN;
	}

	public String getMVM_RES_CD() {
		return MVM_RES_CD;
	}

	public void setMVM_RES_CD(String mVM_RES_CD) {
		MVM_RES_CD = mVM_RES_CD;
	}

	public String getNLR_LCL_NO() {
		return NLR_LCL_NO;
	}

	public void setNLR_LCL_NO(String nLR_LCL_NO) {
		NLR_LCL_NO = nLR_LCL_NO;
	}

	public String getNTFC_DE() {
		return NTFC_DE;
	}

	public void setNTFC_DE(String nTFC_DE) {
		NTFC_DE = nTFC_DE;
	}

	public String getOPERT_DE() {
		return OPERT_DE;
	}

	public void setOPERT_DE(String oPERT_DE) {
		OPERT_DE = oPERT_DE;
	}

	public String getOPE_MAN_ID() {
		return OPE_MAN_ID;
	}

	public void setOPE_MAN_ID(String oPE_MAN_ID) {
		OPE_MAN_ID = oPE_MAN_ID;
	}

	public int getPAR_RDS_NO() {
		return PAR_RDS_NO;
	}

	public void setPAR_RDS_NO(int pAR_RDS_NO) {
		PAR_RDS_NO = pAR_RDS_NO;
	}

	public String getPAR_SIG_CD() {
		return PAR_SIG_CD;
	}

	public void setPAR_SIG_CD(String pAR_SIG_CD) {
		PAR_SIG_CD = pAR_SIG_CD;
	}

	public String getRBP_CN() {
		return RBP_CN;
	}

	public void setRBP_CN(String rBP_CN) {
		RBP_CN = rBP_CN;
	}

	public String getRDS_DPN_SE() {
		return RDS_DPN_SE;
	}

	public void setRDS_DPN_SE(String rDS_DPN_SE) {
		RDS_DPN_SE = rDS_DPN_SE;
	}

	public int getRDS_MAN_NO() {
		return RDS_MAN_NO;
	}

	public void setRDS_MAN_NO(int rDS_MAN_NO) {
		RDS_MAN_NO = rDS_MAN_NO;
	}

	public String getREP_CN() {
		return REP_CN;
	}

	public void setREP_CN(String rEP_CN) {
		REP_CN = rEP_CN;
	}

	public String getRN() {
		return RN;
	}

	public void setRN(String rN) {
		RN = rN;
	}

	public String getRNCHA_R_CD() {
		return RNCHA_R_CD;
	}

	public void setRNCHA_R_CD(String rNCHA_R_CD) {
		RNCHA_R_CD = rNCHA_R_CD;
	}

	public String getRN_CD() {
		return RN_CD;
	}

	public void setRN_CD(String rN_CD) {
		RN_CD = rN_CD;
	}

	public String getRN_DLB_DE() {
		return RN_DLB_DE;
	}

	public void setRN_DLB_DE(String rN_DLB_DE) {
		RN_DLB_DE = rN_DLB_DE;
	}

	public int getROAD_BT() {
		return ROAD_BT;
	}

	public void setROAD_BT(int rOAD_BT) {
		ROAD_BT = rOAD_BT;
	}

	public int getROAD_LT() {
		return ROAD_LT;
	}

	public void setROAD_LT(int rOAD_LT) {
		ROAD_LT = rOAD_LT;
	}

	public int getROAD_PY_LT() {
		return ROAD_PY_LT;
	}

	public void setROAD_PY_LT(int rOAD_PY_LT) {
		ROAD_PY_LT = rOAD_PY_LT;
	}

	public String getROA_CLS_SE() {
		return ROA_CLS_SE;
	}

	public void setROA_CLS_SE(String rOA_CLS_SE) {
		ROA_CLS_SE = rOA_CLS_SE;
	}

	public String getROA_MAN_ES() {
		return ROA_MAN_ES;
	}

	public void setROA_MAN_ES(String rOA_MAN_ES) {
		ROA_MAN_ES = rOA_MAN_ES;
	}

	public String getSIG_CD() {
		return SIG_CD;
	}

	public void setSIG_CD(String sIG_CD) {
		SIG_CD = sIG_CD;
	}

	public String getWDR_RD_CD() {
		return WDR_RD_CD;
	}

	public void setWDR_RD_CD(String wDR_RD_CD) {
		WDR_RD_CD = wDR_RD_CD;
	}
}
