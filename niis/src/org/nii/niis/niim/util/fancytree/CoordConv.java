package org.nii.niis.niim.util.fancytree;

/**
 * 개요
 * - 좌표 변환 클래스
 *  e
 * @author 김재화
 * @version 1.0
 * @created 11-16-2012 
 * 
 * <pre>
 * == 개정이력(Modification Information) ==
 * 
 *   수정일              수정자              수정내용
 *  -------     --------    ---------------------------
 *  2012.11.16   김재화              최초 생성
 *  
 * </pre>
 */
public class CoordConv {

	private static double PI = 3.14159265358979;
	private static double EPSLN = 0.0000000001;
	//private static double S2R = 4.84813681109536E-06;

	private static int X_W2B = 128;
	private static int Y_W2B = -481;
	private static int Z_W2B = -664;

	/**
	 * Degree to Radian
	 * 
	 * @param dInX
	 * @return
	 */
	public double d2R(double dInX) {
		return dInX * PI / 180.0;
	}
	
	/**
	 * Radian to Degree
	 * 
	 * @param radian
	 * @return
	 */
	public double r2D(double radian) {
		return radian * 180.0 / PI;
	}

	public static int KBESSEL = 0;
	public static int KWGS84 = 1;

	public static int KGEO = 0;
	public static int KTMW = 1;
	public static int KTMM = 2;
	public static int KTME = 3;

	public static int KKAT = 4;
	public static int KU52 = 5;
	public static int KU51 = 6;
	public static int UTMK = 7;


	private int m_eSrcEllips;
	private int m_eSrcSystem;
	private int m_eDstEllips;
	private int m_eDstSystem;

	private double m_arMajor[] = new double[2];
	private double m_arMinor[] = new double[2];

	private double m_arScaleFactor[] = new double[8];
	private double m_arLonCenter[] = new double[8];
	private double m_arLatCenter[] = new double[8];
	private double m_arFalseNorthing[] = new double[8];
	private double m_arFalseEasting[] = new double[8];

	private double m_dSrcE0, m_dSrcE1, m_dSrcE2, m_dSrcE3;
	private double /*m_dSrcE,*/ m_dSrcEs, m_dSrcEsp;
	private double m_dSrcMl0, m_dSrcInd;

	private double m_dDstE0, m_dDstE1, m_dDstE2, m_dDstE3;
	private double /*m_dDstE,*/ m_dDstEs, m_dDstEsp;
	private double m_dDstMl0, m_dDstInd;

	private double m_dTemp;
	private double m_dEsTemp;
	private int m_iDeltaX;
	private int m_iDeltaY;
	private int m_iDeltaZ;
	private double m_dDeltaA, m_dDeltaF;

	private double m_dOutX, m_dOutY;
	
	/**
	 * 
	 * 변환된 좌표값 X or E를 리턴한다. <br />
	 * 
	 */
	public double getOutX() {
		return m_dOutX;
	}

	/**
	 * 
	 * 변환된 좌표값 Y or N을 리턴한다. <br />
	 * 
	 */
	public double getOutY() {
		return m_dOutY;
	}
	
	public void setSrcType(int gEllips, int gSystem) {
		double temp;
		m_eSrcEllips = gEllips;
		m_eSrcSystem = gSystem;

		temp = m_arMinor[m_eSrcEllips] / m_arMajor[m_eSrcEllips];
		m_dSrcEs = 1.0 - temp * temp;
		//m_dSrcE = Math.sqrt(m_dSrcEs);
		m_dSrcE0 = e0fn(m_dSrcEs);
		m_dSrcE1 = e1fn(m_dSrcEs);
		m_dSrcE2 = e2fn(m_dSrcEs);
		m_dSrcE3 = e3fn(m_dSrcEs);
		m_dSrcMl0 = m_arMajor[m_eSrcEllips]
				* mlfn(m_dSrcE0, m_dSrcE1, m_dSrcE2, m_dSrcE3,
						m_arLatCenter[m_eSrcSystem]);
		m_dSrcEsp = m_dSrcEs / (1.0 - m_dSrcEs);

		if (m_dSrcEs < 0.00001)
			m_dSrcInd = 1.0;
		else
			m_dSrcInd = 0.0;

		initDatumVar();
	}

	public void setDstType(int gEllips, int gSystem) {
		double temp;
		m_eDstEllips = gEllips;
		m_eDstSystem = gSystem;

		temp = m_arMinor[m_eDstEllips] / m_arMajor[m_eDstEllips];
		m_dDstEs = 1.0 - temp * temp;
		//m_dDstE = Math.sqrt(m_dDstEs);
		m_dDstE0 = e0fn(m_dDstEs);
		m_dDstE1 = e1fn(m_dDstEs);
		m_dDstE2 = e2fn(m_dDstEs);
		m_dDstE3 = e3fn(m_dDstEs);
		m_dDstMl0 = m_arMajor[m_eDstEllips]
				* mlfn(m_dDstE0, m_dDstE1, m_dDstE2, m_dDstE3,
						m_arLatCenter[m_eDstSystem]);
		m_dDstEsp = m_dDstEs / (1.0 - m_dDstEs);

		if (m_dDstEs < 0.00001)
			m_dDstInd = 1.0;
		else
			m_dDstInd = 0.0;

		initDatumVar();
	}

	/**
	 * 
	 * 좌표값을 세팅한다.<br />
	 * @param igEllips 입력타원체
	 * @param igSystem 입력투영
	 * @param ogEllips 출력타원체
	 * @param ogSystem 출력투영
	 * 
	 */
	public void coordSet(int igEllips, int igSystem, int ogEllips, int ogSystem) {
		m_arMajor[KBESSEL] = 6377397.155;
		m_arMinor[KBESSEL] = 6356078.96325;

		m_arMajor[KWGS84] = 6378137.0;
		m_arMinor[KWGS84] = 6356752.3142;

		m_arScaleFactor[KGEO] = 1;
		m_arLonCenter[KGEO] = 0.0;
		m_arLatCenter[KGEO] = 0.0;
		m_arFalseNorthing[KGEO] = 0.0;
		m_arFalseEasting[KGEO] = 0.0;

		m_arScaleFactor[KTMW] = 1;
		m_arLonCenter[KTMW] = 2.18166156499290972;
		m_arLatCenter[KTMW] = 0.66322511575784456;
		m_arFalseNorthing[KTMW] = 600000.0;
		m_arFalseEasting[KTMW] = 200000.0;

		m_arScaleFactor[KTMM] = 1;
		m_arLonCenter[KTMM] = 2.2165681500327985626930872759802;
		m_arLatCenter[KTMM] = 0.66322511575784456;
		m_arFalseNorthing[KTMM] = 600000.0;
		m_arFalseEasting[KTMM] = 200000.0;

		m_arScaleFactor[KTME] = 1;
		m_arLonCenter[KTME] = 2.25147473507268283;
		m_arLatCenter[KTME] = 0.66322511575784456;
		m_arFalseNorthing[KTME] = 600000.0;
		m_arFalseEasting[KTME] = 200000.0;

		m_arScaleFactor[KKAT] = 0.9999;
		m_arLonCenter[KKAT] = 2.23402144255274;
		m_arLatCenter[KKAT] = 0.663225115757845;
		m_arFalseNorthing[KKAT] = 600000.0;
		m_arFalseEasting[KKAT] = 400000.0;

		m_arScaleFactor[KU52] = 0.9996;
		m_arLonCenter[KU52] = 2.25147473507269;
		m_arLatCenter[KU52] = 0.0;
		m_arFalseNorthing[KU52] = 0.0;
		m_arFalseEasting[KU52] = 500000.0;

		m_arScaleFactor[KU51] = 0.9996;
		m_arLonCenter[KU51] = 2.14675497995303;
		m_arLatCenter[KU51] = 0.0;
		m_arFalseNorthing[KU51] = 0.0;
		m_arFalseEasting[KU51] = 500000.0;

		m_arScaleFactor[UTMK] = 0.9996;
		m_arLonCenter[UTMK] = 2.2252947962927679166666666666667;
		m_arLatCenter[UTMK] = 0.6632251157578445555555555555555;
		m_arFalseNorthing[UTMK] = 2000000.0;
		m_arFalseEasting[UTMK] = 1000000.0;


		m_eSrcEllips = igEllips;
		m_eSrcSystem = igSystem;
		m_eDstEllips = ogEllips;
		m_eDstSystem = ogSystem;

		setSrcType(igEllips, igSystem);
		setDstType(ogEllips, ogSystem);
	}

	/**
	 * 
	 * 입력받은 좌표값을 변환한다. <br />
	 * @param dInX 입력X or 입력 E (위도- 도분초 일경우 convert를 이용하여 degree 얻음)
	 * @param dInY 입력Y or 입력 N (경도- 도분초 일경우 convert를 이용하여 degree 얻음)
	 * 
	 */
	public void mapConv(double dInX, double dInY) {
		double dInLon, dInLat;
		double dOutLon, dOutLat;

		double dTmX, dTmY;

		if (m_eSrcSystem == KGEO) {
			dInLon = d2R(dInX);
			dInLat = d2R(dInY);
		} else {
			double con;
			double phi;
			double delta_Phi;
			long i;
			double sin_phi, cos_phi, tan_phi;
			double c, cs, t, ts, n, r, d, ds;
			double f, h, g, temp;

			long max_iter = 6;

			if (m_dSrcInd != 0) {
				f = Math.exp(dInX
						/ (m_arMajor[m_eSrcEllips] * m_arScaleFactor[m_eSrcSystem]));
				g = 0.5 * (f - 1.0 / f);
				temp = m_arLatCenter[m_eSrcSystem]
						+ dInY
						/ (m_arMajor[m_eSrcEllips] * m_arScaleFactor[m_eSrcSystem]);
				h = Math.cos(temp);
				con = Math.sqrt((1.0 - h * h) / (1.0 + g * g));
				dInLat = asinz(con);

				if (temp < 0)
					dInLat *= -1;

				if ((g == 0) && (h == 0))
					dInLon = m_arLonCenter[m_eSrcSystem];
				else
					dInLon = Math.atan(g / h) + m_arLonCenter[m_eSrcSystem];
			}

			dInX -= m_arFalseEasting[m_eSrcSystem];
			dInY -= m_arFalseNorthing[m_eSrcSystem];

			con = (m_dSrcMl0 + dInY / m_arScaleFactor[m_eSrcSystem])
					/ m_arMajor[m_eSrcEllips];
			phi = con;

			i = 0;
			while (true) {
				delta_Phi = ((con + m_dSrcE1 * Math.sin(2.0 * phi) - m_dSrcE2
						* Math.sin(4.0 * phi) + m_dSrcE3 * Math.sin(6.0 * phi)) / m_dSrcE0)
						- phi;
				phi = phi + delta_Phi;
				if (Math.abs(delta_Phi) <= EPSLN)
					break;

				if (i >= max_iter) {
					System.out.println("Coord Convert Error.");
				}
				i++;
			}

			if (Math.abs(phi) < (PI / 2)) {
				sin_phi = Math.sin(phi);
				cos_phi = Math.cos(phi);
				tan_phi = Math.tan(phi);
				c = m_dSrcEsp * cos_phi * cos_phi;
				cs = c * c;
				t = tan_phi * tan_phi;
				ts = t * t;
				con = 1.0 - m_dSrcEs * sin_phi * sin_phi;
				n = m_arMajor[m_eSrcEllips] / Math.sqrt(con);
				r = n * (1.0 - m_dSrcEs) / con;
				d = dInX / (n * m_arScaleFactor[m_eSrcSystem]);
				ds = d * d;
				dInLat = phi
						- (n * tan_phi * ds / r)
						* (0.5 - ds
								/ 24.0
								* (5.0 + 3.0 * t + 10.0 * c - 4.0 * cs - 9.0
										* m_dSrcEsp - ds
										/ 30.0
										* (61.0 + 90.0 * t + 298.0 * c + 45.0
												* ts - 252.0 * m_dSrcEsp - 3.0 * cs)));
				dInLon = m_arLonCenter[m_eSrcSystem]
						+ (d
								* (1.0 - ds
										/ 6.0
										* (1.0 + 2.0 * t + c - ds
												/ 20.0
												* (5.0 - 2.0 * c + 28.0 * t
														- 3.0 * cs + 8.0
														* m_dSrcEsp + 24.0 * ts))) / cos_phi);
			} else {
				dInLat = PI * 0.5 * Math.sin(dInY);
				dInLon = m_arLonCenter[m_eSrcSystem];
			}
		}

		if (m_eSrcEllips == m_eDstEllips) {
			dOutLon = dInLon;
			dOutLat = dInLat;
		} else {
			double dRm, dRn;
			double dDeltaPhi, dDeltaLamda;

			dRm = m_arMajor[m_eSrcEllips]
					* (1.0 - m_dEsTemp)
					/ Math.pow(
							1.0 - m_dEsTemp * Math.sin(dInLat)
									* Math.sin(dInLat), 1.5);
			dRn = m_arMajor[m_eSrcEllips]
					/ Math.sqrt(1.0 - m_dEsTemp * Math.sin(dInLat)
							* Math.sin(dInLat));

			dDeltaPhi = ((((-m_iDeltaX * Math.sin(dInLat) * Math.cos(dInLon) - m_iDeltaY
					* Math.sin(dInLat) * Math.sin(dInLon)) + m_iDeltaZ
					* Math.cos(dInLat)) + m_dDeltaA * dRn * m_dEsTemp
					* Math.sin(dInLat) * Math.cos(dInLat)
					/ m_arMajor[m_eSrcEllips]) + m_dDeltaF
					* (dRm / m_dTemp + dRn * m_dTemp) * Math.sin(dInLat)
					* Math.cos(dInLat))
					/ dRm;
			dDeltaLamda = (-m_iDeltaX * Math.sin(dInLon) + m_iDeltaY
					* Math.cos(dInLon))
					/ (dRn * Math.cos(dInLat));

			dOutLat = dInLat + dDeltaPhi;
			dOutLon = dInLon + dDeltaLamda;
		}

		if (m_eDstSystem == KGEO) {
			m_dOutX = r2D(dOutLon);
			m_dOutY = r2D(dOutLat);
		} else {
			double delta_lon;
			double sin_phi, cos_phi;
			double al, als;
			double b, c, t, tq;
			double con, n, ml;

			delta_lon = dOutLon - m_arLonCenter[m_eDstSystem];
			sin_phi = Math.sin(dOutLat);
			cos_phi = Math.cos(dOutLat);

			if (m_dDstInd != 0) {
				b = cos_phi * Math.sin(delta_lon);
				if ((Math.abs(Math.abs(b) - 1.0)) < 0.0000000001) {
					System.out.println("Coord Convert Error.");
				}
			} else {
				b = 0;
				dTmX = 0.5 * m_arMajor[m_eDstEllips]
						* m_arScaleFactor[m_eDstSystem]
						* Math.log((1.0 + b) / (1.0 - b));
				con = Math.acos(cos_phi * Math.cos(delta_lon)
						/ Math.sqrt(1.0 - b * b));
				if (dOutLat < 0) {
					con = -con;
					dTmY = m_arMajor[m_eDstEllips]
							* m_arScaleFactor[m_eDstSystem]
							* (con - m_arLatCenter[m_eDstSystem]);
				}
			}

			al = cos_phi * delta_lon;
			als = al * al;
			c = m_dDstEsp * cos_phi * cos_phi;
			tq = Math.tan(dOutLat);
			t = tq * tq;
			con = 1.0 - m_dDstEs * sin_phi * sin_phi;
			n = m_arMajor[m_eDstEllips] / Math.sqrt(con);
			ml = m_arMajor[m_eDstEllips]
					* mlfn(m_dDstE0, m_dDstE1, m_dDstE2, m_dDstE3, dOutLat);

			dTmX = m_arScaleFactor[m_eDstSystem]
					* n
					* al
					* (1.0 + als
							/ 6.0
							* (1.0 - t + c + als
									/ 20.0
									* (5.0 - 18.0 * t + t * t + 72.0 * c - 58.0 * m_dDstEsp)))
					+ m_arFalseEasting[m_eDstSystem];
			dTmY = m_arScaleFactor[m_eDstSystem]
					* (ml - m_dDstMl0 + n
							* tq
							* (als * (0.5 + als
									/ 24.0
									* (5.0 - t + 9.0 * c + 4.0 * c * c + als
											/ 30.0
											* (61.0 - 58.0 * t + t * t + 600.0
													* c - 330.0 * m_dDstEsp)))))
					+ m_arFalseNorthing[m_eDstSystem];

			m_dOutX = dTmX;
			m_dOutY = dTmY;
		}
	}

	public void initDatumVar() {
		int iDefFact;
		double dF;

		iDefFact = m_eSrcEllips - m_eDstEllips;
		m_iDeltaX = iDefFact * X_W2B;
		m_iDeltaY = iDefFact * Y_W2B;
		m_iDeltaZ = iDefFact * Z_W2B;

		m_dTemp = m_arMinor[m_eSrcEllips] / m_arMajor[m_eSrcEllips];
		dF = 1.0 - m_dTemp;
		m_dEsTemp = 1.0 - m_dTemp * m_dTemp;

		m_dDeltaA = m_arMajor[m_eDstEllips] - m_arMajor[m_eSrcEllips];
		m_dDeltaF = m_arMinor[m_eSrcEllips] / m_arMajor[m_eSrcEllips]
				- m_arMinor[m_eDstEllips] / m_arMajor[m_eDstEllips];
	}

	public double asinz(double value) {
		if (Math.abs(value) > 1.0)
			value = (value > 0 ? 1 : -1);

		return Math.asin(value);
	}

	public double e0fn(double x) {
		return 1.0 - 0.25 * x * (1.0 + x / 16.0 * (3.0 + 1.25 * x));
	}

	public double e1fn(double x) {
		return 0.375 * x * (1.0 + 0.25 * x * (1.0 + 0.46875 * x));
	}

	public double e2fn(double x) {
		return 0.05859375 * x * x * (1.0 + 0.75 * x);
	}

	public double e3fn(double x) {
		return x * x * x * (35.0 / 3072.0);
	}

	public double e4fn(double x) {
		double con, com;

		con = 1.0 + x;
		com = 1.0 - x;
		return Math.sqrt(Math.pow(con, con) * Math.pow(com, com));
	}

	public double mlfn(double e0, double e1, double e2, double e3, double phi) {
		return e0 * phi - e1 * Math.sin(2.0 * phi) + e2 * Math.sin(4.0 * phi)
				- e3 * Math.sin(6.0 * phi);
	}
	
	/**
	 * 
	 * 도, 분, 초 값을 실수로 변환한다. <br />
	 * @param deg 도
	 * @param min 분
	 * @param sec 초
	 * @return 변환된 실수 값 - double 로 리턴 
	 * 
	 */
	public double convert(int deg, int min, double sec) {
		return deg + min / 60.0 + sec / 3600.0;
	}
	
	
	
	/**
	 * 
	 * 실수값을 도, 분, 초로 변환한다. <br />
	 * @param realNum 실수값
	 * @return 변환된 도, 분, 초 값 - 길이가 3인 double 배열로 리턴
	 * 
	 */
	public double[] deconvert(double realNum) {
		
		int realNumDeg = (int)realNum; 
		int realNumMin =  (int)((realNum - realNumDeg) *60.0); 
		double realNumSec = (realNum - realNumDeg - (realNumMin /60.0))*3600.0;
		
		double degMinSec[] = new double[3];
		
		degMinSec[0] =  realNumDeg;
		degMinSec[1] =  realNumMin;
		degMinSec[2] =  realNumSec;
	
		
		return degMinSec;
	}

	public void test() {

		double dInX = 127.0542399444;
		double dInY = 37.2755146944;

		coordSet(KWGS84, KGEO, KWGS84, KTMM);
		//System.out.println(dInX + " " + dInY);
		mapConv(dInX, dInY);

		//System.out.println(m_dOutX);
		//System.out.println(m_dOutY);
		
		System.out.println(getOutX());
		System.out.println(getOutY());
	}

	public void test(String origin, Double xmin, Double ymin){
		double dInX = 1000506.0055;
		double dInY = 1953775.714;
		System.out.println("xmin>>>>>" + xmin);
		System.out.println("ymin>>>>>" + ymin);
		coordSet(KWGS84, UTMK, KWGS84, KTMM);
		
		mapConv(dInX, ymin);
		
		System.out.println("x>>>>>>" + getOutX());
		System.out.println("y>>>>>>" + getOutY());
		
	}

}
