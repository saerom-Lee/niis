package org.nii.niis.niim.util;

public class BlackList {
	
	 /**
     * XSS 방지 처리.
     * 
     * @param data
     * @return
     */
	
    public static String unscript(String data) {
        if (data == null || data.trim().equals("")) {
            return "";
        }
        
        String ret = data;
                
        ret = ret.replaceAll("<(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;script");
        ret = ret.replaceAll("</(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;/script");
        
        ret = ret.replaceAll("<(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;object");
        ret = ret.replaceAll("</(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;/object");
        
        ret = ret.replaceAll("<(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;applet");
        ret = ret.replaceAll("</(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;/applet");
        
        ret = ret.replaceAll("<(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
        ret = ret.replaceAll("</(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
        
        ret = ret.replaceAll("<(F|f)(O|o)(R|r)(M|m)", "&lt;form");
        ret = ret.replaceAll("</(F|f)(O|o)(R|r)(M|m)", "&lt;form");
            
        ret = ret.replaceAll("<(J|j)(A|a)(V|v)(A|a)(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;javascript");
        ret = ret.replaceAll("</(J|j)(A|a)(V|v)(A|a)(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;/javascript");
        
        ret = ret.replaceAll("<(I|i)(F|f)(R|r)(A|a)(M|m)(E|e)", "&lt;iframe");
        ret = ret.replaceAll("</(I|i)(F|f)(R|r)(A|a)(M|m)(E|e)", "&lt;iframe");

        ret = ret.replaceAll("<(D|d)(O|o)(C|c)(U|u)(M|m)(E|e)(N|n)(T|t)", "&lt;document");
        ret = ret.replaceAll("</(D|d)(O|o)(C|c)(U|u)(M|m)(E|e)(N|n)(T|t)", "&lt;document");
 
        ret = ret.replaceAll("<(V|v)(B|b)(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;vbscript");
        ret = ret.replaceAll("</(V|v)(B|b)(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;vbscript");
      
        ret = ret.replaceAll("<(F|f)(R|r)(A|a)(M|m)(E|e)", "&lt;frame");
        ret = ret.replaceAll("</(F|f)(R|r)(A|a)(M|m)(E|e)", "&lt;frame");
        
        ret = ret.replaceAll("<(L|l)(A|a)(Y|y)(E|e)(R|r)", "&lt;layer");
        ret = ret.replaceAll("</(L|l)(A|a)(Y|y)(E|e)(R|r)", "&lt;layer");
      
        ret = ret.replaceAll("<(F|f)(R|r)(A|a)(M|m)(E|e)(S|s)(E|e)(T|t)", "&lt;frameset");
        ret = ret.replaceAll("</(F|f)(R|r)(A|a)(M|m)(E|e)(S|s)(E|e)(T|t)", "&lt;frameset");

        ret = ret.replaceAll("<(B|b)(G|g)(S|s)(O|o)(U|u)(N|n)(D|d)", "&lt;bgsound");
        ret = ret.replaceAll("</(B|b)(G|g)(S|s)(O|o)(U|u)(N|n)(D|d)", "&lt;bgsound");

        ret = ret.replaceAll("<(A|a)(L|l)(E|e)(R|r)(T|t)", "&lt;alert");
        ret = ret.replaceAll("</(A|a)(L|l)(E|e)(R|r)(T|t)", "&lt;alert");
     
        ret = ret.replaceAll("<(O|o)(N|n)(B|b)(L|l)(U|u)(R|r)", "&lt;onblur");
        ret = ret.replaceAll("</(O|o)(N|n)(B|b)(L|l)(U|u)(R|r)", "&lt;/onblur");
        
        ret = ret.replaceAll("<(O|o)(N|n)(C|c)(H|h)(A|a)(N|n)(G|g)(E|e)", "&lt;onchange");
        ret = ret.replaceAll("</(O|o)(N|n)(C|c)(H|h)(A|a)(N|n)(G|g)(E|e)", "&lt;onchange");

        ret = ret.replaceAll("<(O|o)(N|n)(D|d)(B|b)(L|l)(C|c)(L|l)(I|i)(C|c)(K|k)", "&lt;ondblclick");
        ret = ret.replaceAll("</(O|o)(N|n)(D|d)(B|b)(L|l)(C|c)(L|l)(I|i)(C|c)(K|k)", "&lt;ondblclick");
        
        ret = ret.replaceAll("<(O|o)(N|n)(E|e)(R|r)(R|r)(O|o)(R|r)", "&lt;onerror");
        ret = ret.replaceAll("</(O|o)(N|n)(E|e)(R|r)(R|r)(O|o)(R|r)", "&lt;onerror");      
      
        ret = ret.replaceAll("<(O|o)(N|n)(F|f)(O|o)(C|c)(U|u)(S|s)", "&lt;onfocus");
        ret = ret.replaceAll("</(O|o)(N|n)(F|f)(O|o)(C|c)(U|u)(S|s)", "&lt;onfocus");       
        
        ret = ret.replaceAll("<(O|o)(N|n)(L|l)(O|o)(A|a)(D|d)", "&lt;onload");
        ret = ret.replaceAll("</(O|o)(N|n)(L|l)(O|o)(A|a)(D|d)", "&lt;onload");
      
        ret = ret.replaceAll("<(O|o)(N|n)(M|m)(O|o)(U|u)(S|s)(E|e)", "&lt;onmouse");
        ret = ret.replaceAll("</(O|o)(N|n)(M|m)(O|o)(U|u)(S|s)(E|e)", "&lt;onmouse");

        ret = ret.replaceAll("<(O|o)(N|n)(S|s)(U|u)(B|b)(M|m)(I|i)(T|t)", "&lt;onsubmit");
        ret = ret.replaceAll("</(O|o)(N|n)(S|s)(U|u)(B|b)(M|m)(I|i)(T|t)", "&lt;onsubmit");

        ret = ret.replaceAll("<(O|o)(N|n)(U|u)(N|n)(L|l)(O|o)(A|a)(D|d)", "&lt;onunload");
        ret = ret.replaceAll("</(O|o)(N|n)(U|u)(N|n)(L|l)(O|o)(A|a)(D|d)", "&lt;onunload");
                        
		ret = ret.replaceAll("&", "&amp;");      
		ret = ret.replaceAll("<", "&lt;");
		ret = ret.replaceAll(">", "&gt;");       		
		ret = ret.replaceAll("\"", "&quot;");
		ret = ret.replaceAll("\n", "<br />");
		
        return ret;
    }
    
	 /**
     * XSS 방지 처리.
     * 
     * @param data
     * @return
     */
	
    public static String getStrCnvrXss(String data) {
        if (data == null || data.trim().equals("")) {
            return "";
        }
        
        String ret = data;
              
		ret = ret.replaceAll("&", "&amp;");      
		ret = ret.replaceAll("<", "&lt;");
		ret = ret.replaceAll(">", "&gt;");       
		ret = ret.replaceAll("\"", "&quot;");
		ret = ret.replaceAll("\n", "<br />");
		
        return ret;
    }
    
    public static String getXssCnvrStr(String data) {
        if (data == null || data.trim().equals("")) {
            return "";
        }
        
        String ret = data;
              
		ret = ret.replaceAll("&amp;", "&");      
		ret = ret.replaceAll("&lt;", "<");
		ret = ret.replaceAll("&gt;", ">");       
		ret = ret.replaceAll("&quot;", "\"");
		ret = ret.replaceAll("<br/>", "\n");
		
        return ret;
    }
}