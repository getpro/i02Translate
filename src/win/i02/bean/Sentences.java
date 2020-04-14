/**
  * Copyright 2020 bejson.com 
  */
package win.i02.bean;
import java.util.List;

/**
 * Auto-generated: 2020-04-14 9:24:28
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Sentences {

    private String trans;
    private String orig;
    private int backend;
    private List<Translation_engine_debug_info> translation_engine_debug_info;
    public void setTrans(String trans) {
         this.trans = trans;
     }
     public String getTrans() {
         return trans;
     }

    public void setOrig(String orig) {
         this.orig = orig;
     }
     public String getOrig() {
         return orig;
     }

    public void setBackend(int backend) {
         this.backend = backend;
     }
     public int getBackend() {
         return backend;
     }

    public void setTranslation_engine_debug_info(List<Translation_engine_debug_info> translation_engine_debug_info) {
         this.translation_engine_debug_info = translation_engine_debug_info;
     }
     public List<Translation_engine_debug_info> getTranslation_engine_debug_info() {
         return translation_engine_debug_info;
     }

}