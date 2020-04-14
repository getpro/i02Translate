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
public class GoogleTranslateBean {

    private List<Sentences> sentences;
    private String src;
    private int confidence;
    private Spell spell;
    private Ld_result ld_result;
    public void setSentences(List<Sentences> sentences) {
         this.sentences = sentences;
     }
     public List<Sentences> getSentences() {
         return sentences;
     }

    public void setSrc(String src) {
         this.src = src;
     }
     public String getSrc() {
         return src;
     }

    public void setConfidence(int confidence) {
         this.confidence = confidence;
     }
     public int getConfidence() {
         return confidence;
     }

    public void setSpell(Spell spell) {
         this.spell = spell;
     }
     public Spell getSpell() {
         return spell;
     }

    public void setLd_result(Ld_result ld_result) {
         this.ld_result = ld_result;
     }
     public Ld_result getLd_result() {
         return ld_result;
     }

}