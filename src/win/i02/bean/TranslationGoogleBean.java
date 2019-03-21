package win.i02.bean;

/**
 *
 * @author irskj
 * @date 2017/11/21
 */
public class TranslationGoogleBean{
    /**
     * raw :
     * from : {"language":{"didYouMean":false,"iso":"en"},"text":{"didYouMean":true,"autoCorrected":false,"value":"Classes that can be used to bootstrap and launch a Spring application from a Java main method."}}
     * text : 可用于从Java主方法引导和启动Spring应用程序的类。默认情况下，类将执行以下步骤来引导您的应用程序
     */
    private String raw;
    private FromEntity from;
    private String text;
    private String code;

    @Override
    public String toString() {
        if(text==null){
            return "请求失败";
        }
        return text;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public void setFrom(FromEntity from) {
        this.from = from;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRaw() {
        return raw;
    }

    public FromEntity getFrom() {
        return from;
    }

    public String getText() {
        return text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public class FromEntity {
        /**
         * language : {"didYouMean":false,"iso":"en"}
         * text : {"didYouMean":true,"autoCorrected":false,"value":"Classes that can be used to bootstrap and launch a Spring application from a Java main method."}
         */
        private LanguageEntity language;
        private TextEntity text;

        public void setLanguage(LanguageEntity language) {
            this.language = language;
        }

        public void setText(TextEntity text) {
            this.text = text;
        }

        public LanguageEntity getLanguage() {
            return language;
        }

        public TextEntity getText() {
            return text;
        }

        public class LanguageEntity {
            /**
             * didYouMean : false
             * iso : en
             */
            private boolean didYouMean;
            private String iso;

            public void setDidYouMean(boolean didYouMean) {
                this.didYouMean = didYouMean;
            }

            public void setIso(String iso) {
                this.iso = iso;
            }

            public boolean isDidYouMean() {
                return didYouMean;
            }

            public String getIso() {
                return iso;
            }
        }

        public class TextEntity {
            /**
             * didYouMean : true
             * autoCorrected : false
             * value : Classes that can be used to bootstrap and launch a Spring application from a Java main method.
             */
            private boolean didYouMean;
            private boolean autoCorrected;
            private String value;

            public void setDidYouMean(boolean didYouMean) {
                this.didYouMean = didYouMean;
            }

            public void setAutoCorrected(boolean autoCorrected) {
                this.autoCorrected = autoCorrected;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public boolean isDidYouMean() {
                return didYouMean;
            }

            public boolean isAutoCorrected() {
                return autoCorrected;
            }

            public String getValue() {
                return value;
            }
        }
    }
//    String text;
//    String raw;
//    From from;
//
//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
//
//    public String getRaw() {
//        return raw;
//    }
//
//    public void setRaw(String raw) {
//        this.raw = raw;
//    }
//
//    public From getFrom() {
//        return from;
//    }
//
//    public void setFrom(From from) {
//        this.from = from;
//    }
//
//    public static class From {
//        Language language;
//        Text text;
//
//        public Language getLanguage() {
//            return language;
//        }
//
//        public void setLanguage(Language language) {
//            this.language = language;
//        }
//
//        public Text getText() {
//            return text;
//        }
//
//        public void setText(Text text) {
//            this.text = text;
//        }
//    }
//
//    public static class Language {
//        boolean didYouMean;
//        String iso;
//
//        public boolean isDidYouMean() {
//            return didYouMean;
//        }
//
//        public void setDidYouMean(boolean didYouMean) {
//            this.didYouMean = didYouMean;
//        }
//
//        public String getIso() {
//            return iso;
//        }
//
//        public void setIso(String iso) {
//            this.iso = iso;
//        }
//    }
//
//    public static class Text {
//        boolean autoCorrected;
//        boolean didYouMean;
//        String value;
//
//        public boolean isAutoCorrected() {
//            return autoCorrected;
//        }
//
//        public void setAutoCorrected(boolean autoCorrected) {
//            this.autoCorrected = autoCorrected;
//        }
//
//        public boolean isDidYouMean() {
//            return didYouMean;
//        }
//
//        public void setDidYouMean(boolean didYouMean) {
//            this.didYouMean = didYouMean;
//        }
//
//        public String getValue() {
//            return value;
//        }
//
//        public void setValue(String value) {
//            this.value = value;
//        }
//    }


}
