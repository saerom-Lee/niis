package org.nii.niis.niim.service;

import java.io.Serializable;

import com.vividsolutions.jts.geom.Geometry;

@SuppressWarnings("serial")
public class ZipCodeVO implements Serializable {
	private String  BDTYP_CD			= "";
	private String  BD_MGT_SN			= "";
	private int  		BSI_INT_SN		= 0;
	private String  BSI_ZON_NO			= "";
	private String  BULD_MEMO			= "";
	private int  		BULD_MNNM		= 0;
	private String  BULD_NM				= "";
	private String  BULD_NM_DC			= "";
	private String  BULD_SE_CD			= "";
	private int  		BULD_SLNO		= 0;
	private String  BULD_STTUS			= "";
	private String  BUL_DPN_SE			= "";
	private String  BUL_ENG_NM			= "";
	private int  		BUL_MAN_NO		= 0;
	private String  COMPET_DE			= "";
	private String  EMD_CD				= "";
	private int  		EQB_MAN_SN		= 0;
	private String  ETC_BUL_NM			= "";
	private int  		GRO_FLO_CO		= 0;
	private int  		IMA_FIL_SN		= 0;
	private String  INPUT_MTHD			= "";
	private String  INPUT_STEP			= "";
	private String  ISSU_YN				= "";
	private String  LI_CD				= "";
	private int  		LNBR_MNNM		= 0;
	private int  		LNBR_SLNO		= 0;
	private String  MNTN_YN				= "";
	private String  MVMN_DE				= "";
	private String  MVMN_RESN			= "";
	private String  MVM_RES_CD			= "";
	private String  NTFC_DE				= "";
	private String  NTI_TRG_YN			= "";
	private String  OPERT_DE			= "";
	private String  OPE_MAN_ID			= "";
	private String  POS_BUL_NM			= "";
	private String  POS_BUL_YN			= "";
	private int  		RDS_MAN_NO		= 0;
	private String  RDS_SIG_CD			= "";
	private String  REG_PUB_NM			= "";
	private String  RN_CD				= "";
	private String  SIG_CD				= "";
	private int  		UND_FLO_CO		= 0;
	private String  ZIP					= "";
	private String  ZIP_BUL_NM			= "";
	private String  ZIP_NO				= "";
	private String  POINT_X				= "";
	private String  POINT_Y				= "";
	
	private String  DONG_NM				= "";
	private String JIBUN				= "";

	private byte[]  wkb_geometry = null;

	Geometry geom = null;
	
	
	
	public String getJIBUN() {
		return JIBUN;
	}

	public void setJIBUN(String jIBUN) {
		JIBUN = jIBUN;
	}

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

	public String getBDTYP_CD() {
		return BDTYP_CD;
	}

	public void setBDTYP_CD(String bDTYP_CD) {
		BDTYP_CD = bDTYP_CD;
	}

	public String getBD_MGT_SN() {
		return BD_MGT_SN;
	}

	public void setBD_MGT_SN(String bD_MGT_SN) {
		BD_MGT_SN = bD_MGT_SN;
	}

	public int getBSI_INT_SN() {
		return BSI_INT_SN;
	}

	public void setBSI_INT_SN(int bSI_INT_SN) {
		BSI_INT_SN = bSI_INT_SN;
	}

	public String getBSI_ZON_NO() {
		return BSI_ZON_NO;
	}

	public void setBSI_ZON_NO(String bSI_ZON_NO) {
		BSI_ZON_NO = bSI_ZON_NO;
	}

	public String getBULD_MEMO() {
		return BULD_MEMO;
	}

	public void setBULD_MEMO(String bULD_MEMO) {
		BULD_MEMO = bULD_MEMO;
	}

	public int getBULD_MNNM() {
		return BULD_MNNM;
	}

	public void setBULD_MNNM(int bULD_MNNM) {
		BULD_MNNM = bULD_MNNM;
	}

	public String getBULD_NM() {
		return BULD_NM;
	}

	public void setBULD_NM(String bULD_NM) {
		BULD_NM = bULD_NM;
	}

	public String getBULD_NM_DC() {
		return BULD_NM_DC;
	}

	public void setBULD_NM_DC(String bULD_NM_DC) {
		BULD_NM_DC = bULD_NM_DC;
	}

	public String getBULD_SE_CD() {
		return BULD_SE_CD;
	}

	public void setBULD_SE_CD(String bULD_SE_CD) {
		BULD_SE_CD = bULD_SE_CD;
	}

	public int getBULD_SLNO() {
		return BULD_SLNO;
	}

	public void setBULD_SLNO(int bULD_SLNO) {
		BULD_SLNO = bULD_SLNO;
	}

	public String getBULD_STTUS() {
		return BULD_STTUS;
	}

	public void setBULD_STTUS(String bULD_STTUS) {
		BULD_STTUS = bULD_STTUS;
	}

	public String getBUL_DPN_SE() {
		return BUL_DPN_SE;
	}

	public void setBUL_DPN_SE(String bUL_DPN_SE) {
		BUL_DPN_SE = bUL_DPN_SE;
	}

	public String getBUL_ENG_NM() {
		return BUL_ENG_NM;
	}

	public void setBUL_ENG_NM(String bUL_ENG_NM) {
		BUL_ENG_NM = bUL_ENG_NM;
	}

	public int getBUL_MAN_NO() {
		return BUL_MAN_NO;
	}

	public void setBUL_MAN_NO(int bUL_MAN_NO) {
		BUL_MAN_NO = bUL_MAN_NO;
	}

	public String getCOMPET_DE() {
		return COMPET_DE;
	}

	public void setCOMPET_DE(String cOMPET_DE) {
		COMPET_DE = cOMPET_DE;
	}

	public String getEMD_CD() {
		return EMD_CD;
	}

	public void setEMD_CD(String eMD_CD) {
		EMD_CD = eMD_CD;
	}

	public int getEQB_MAN_SN() {
		return EQB_MAN_SN;
	}

	public void setEQB_MAN_SN(int eQB_MAN_SN) {
		EQB_MAN_SN = eQB_MAN_SN;
	}

	public String getETC_BUL_NM() {
		return ETC_BUL_NM;
	}

	public void setETC_BUL_NM(String eTC_BUL_NM) {
		ETC_BUL_NM = eTC_BUL_NM;
	}

	public int getGRO_FLO_CO() {
		return GRO_FLO_CO;
	}

	public void setGRO_FLO_CO(int gRO_FLO_CO) {
		GRO_FLO_CO = gRO_FLO_CO;
	}

	public int getIMA_FIL_SN() {
		return IMA_FIL_SN;
	}

	public void setIMA_FIL_SN(int iMA_FIL_SN) {
		IMA_FIL_SN = iMA_FIL_SN;
	}

	public String getINPUT_MTHD() {
		return INPUT_MTHD;
	}

	public void setINPUT_MTHD(String iNPUT_MTHD) {
		INPUT_MTHD = iNPUT_MTHD;
	}

	public String getINPUT_STEP() {
		return INPUT_STEP;
	}

	public void setINPUT_STEP(String iNPUT_STEP) {
		INPUT_STEP = iNPUT_STEP;
	}

	public String getISSU_YN() {
		return ISSU_YN;
	}

	public void setISSU_YN(String iSSU_YN) {
		ISSU_YN = iSSU_YN;
	}

	public String getLI_CD() {
		return LI_CD;
	}

	public void setLI_CD(String lI_CD) {
		LI_CD = lI_CD;
	}

	public int getLNBR_MNNM() {
		return LNBR_MNNM;
	}

	public void setLNBR_MNNM(int lNBR_MNNM) {
		LNBR_MNNM = lNBR_MNNM;
	}

	public int getLNBR_SLNO() {
		return LNBR_SLNO;
	}

	public void setLNBR_SLNO(int lNBR_SLNO) {
		LNBR_SLNO = lNBR_SLNO;
	}

	public String getMNTN_YN() {
		return MNTN_YN;
	}

	public void setMNTN_YN(String mNTN_YN) {
		MNTN_YN = mNTN_YN;
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

	public String getNTFC_DE() {
		return NTFC_DE;
	}

	public void setNTFC_DE(String nTFC_DE) {
		NTFC_DE = nTFC_DE;
	}

	public String getNTI_TRG_YN() {
		return NTI_TRG_YN;
	}

	public void setNTI_TRG_YN(String nTI_TRG_YN) {
		NTI_TRG_YN = nTI_TRG_YN;
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

	public String getPOS_BUL_NM() {
		return POS_BUL_NM;
	}

	public void setPOS_BUL_NM(String pOS_BUL_NM) {
		POS_BUL_NM = pOS_BUL_NM;
	}

	public String getPOS_BUL_YN() {
		return POS_BUL_YN;
	}

	public void setPOS_BUL_YN(String pOS_BUL_YN) {
		POS_BUL_YN = pOS_BUL_YN;
	}

	public int getRDS_MAN_NO() {
		return RDS_MAN_NO;
	}

	public void setRDS_MAN_NO(int rDS_MAN_NO) {
		RDS_MAN_NO = rDS_MAN_NO;
	}

	public String getRDS_SIG_CD() {
		return RDS_SIG_CD;
	}

	public void setRDS_SIG_CD(String rDS_SIG_CD) {
		RDS_SIG_CD = rDS_SIG_CD;
	}

	public String getREG_PUB_NM() {
		return REG_PUB_NM;
	}

	public void setREG_PUB_NM(String rEG_PUB_NM) {
		REG_PUB_NM = rEG_PUB_NM;
	}

	public String getRN_CD() {
		return RN_CD;
	}

	public void setRN_CD(String rN_CD) {
		RN_CD = rN_CD;
	}

	public String getSIG_CD() {
		return SIG_CD;
	}

	public void setSIG_CD(String sIG_CD) {
		SIG_CD = sIG_CD;
	}

	public int getUND_FLO_CO() {
		return UND_FLO_CO;
	}

	public void setUND_FLO_CO(int uND_FLO_CO) {
		UND_FLO_CO = uND_FLO_CO;
	}

	public String getZIP() {
		return ZIP;
	}

	public void setZIP(String zIP) {
		ZIP = zIP;
	}

	public String getZIP_BUL_NM() {
		return ZIP_BUL_NM;
	}

	public void setZIP_BUL_NM(String zIP_BUL_NM) {
		ZIP_BUL_NM = zIP_BUL_NM;
	}

	public String getZIP_NO() {
		return ZIP_NO;
	}

	public void setZIP_NO(String zIP_NO) {
		ZIP_NO = zIP_NO;
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
	
	public String getDONG_NM() {
		return DONG_NM;
	}

	public void setDONG_NM(String dONG_NM) {
		DONG_NM = dONG_NM;
	}
}
