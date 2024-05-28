package CKNB;

public class CKNBClass {
	private static native int WMGen(byte []openPath, byte []savePath, byte []waterMark, float weight);
	private static native int WMDet(byte []openPath, byte []waterMark);
	public static int LoadLibrary(String rootPath)
	{
		try
		{
			System.load(rootPath + "CKnB_JNI.dll");
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage() + "LoadLibrary Error");
			return -1;
		}
		return 1;
	}
	
	public static int Embedding(String jOpenPath, String jSavePath, String jWatermark, float weight)
	{
		int Ret = 0;
		byte []OpenPath = null;
		byte []SavePath= null;
		byte []Watermark= null;
		try
		{
			OpenPath = jOpenPath.getBytes("KSC5601");
			SavePath= jSavePath.getBytes("KSC5601");
			Watermark= jWatermark.getBytes("KSC5601");
			
			Ret = WMGen(OpenPath, SavePath, Watermark, weight);
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage() + "Embedding Error");
			return -1;
		}
		finally
		{
			OpenPath = null; SavePath = null; Watermark = null;
		}
		return Ret;
	}
	
	public static String Detecting(String jOpenPath)
	{
		int Ret = 0;
		String byteToString = null;
		byte []OpenPath  = null;
		byte []WaterMark = null;
		
		try		
		{
			OpenPath  = jOpenPath.getBytes("KSC5601");
			WaterMark = new byte [128];
			Ret = WMDet(OpenPath, WaterMark);
			if(Ret == 1)
			{
				byteToString = new String(WaterMark, "KSC5601");
				return byteToString;
			}
			return null;
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage() + "Detecting Error");
			return null;
		}
		finally
		{
			OpenPath = null; WaterMark = null;
			byteToString = null;
		}
	}
}
