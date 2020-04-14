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
public class Ld_result {

    private List<String> srclangs;
    private List<Integer> srclangs_confidences;
    private List<String> extended_srclangs;
    public void setSrclangs(List<String> srclangs) {
         this.srclangs = srclangs;
     }
     public List<String> getSrclangs() {
         return srclangs;
     }

    public void setSrclangs_confidences(List<Integer> srclangs_confidences) {
         this.srclangs_confidences = srclangs_confidences;
     }
     public List<Integer> getSrclangs_confidences() {
         return srclangs_confidences;
     }

    public void setExtended_srclangs(List<String> extended_srclangs) {
         this.extended_srclangs = extended_srclangs;
     }
     public List<String> getExtended_srclangs() {
         return extended_srclangs;
     }

}