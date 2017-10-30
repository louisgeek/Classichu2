package com.classichu.classichu2.logic.login.bean;

import java.util.List;

/**
 * Created by Classichu on 2017-7-17.
 */

public class UserLoginBean {

        /**
         * LonginUser : {"YHID":"2000002","YHXM":"王小王","MRBZ":0,"JGID":"1","YHDM":"5820","YHXK":null}
         * TimeVo : {"itemName":null,"Time":"2017-07-17 18:17:31"}
         * Areas : [{"itemName":null,"YGDM":"2000002","KSDM":"1031","MRBZ":0,"KSMC":"一病区"},{"itemName":null,"YGDM":"2000002","KSDM":"1032","MRBZ":0,"KSMC":"二病区"},{"itemName":null,"YGDM":"2000002","KSDM":"1033","MRBZ":1,"KSMC":"三病区"},{"itemName":null,"YGDM":"2000002","KSDM":"1034","MRBZ":0,"KSMC":"五病区"},{"itemName":null,"YGDM":"2000002","KSDM":"1035","MRBZ":0,"KSMC":"六病区"},{"itemName":null,"YGDM":"2000002","KSDM":"1036","MRBZ":0,"KSMC":"七病区"},{"itemName":null,"YGDM":"2000002","KSDM":"1037","MRBZ":0,"KSMC":"八病区"},{"itemName":null,"YGDM":"2000002","KSDM":"1038","MRBZ":0,"KSMC":"九病区"},{"itemName":null,"YGDM":"2000002","KSDM":"1226","MRBZ":0,"KSMC":"入院准备中心"},{"itemName":null,"YGDM":"2000002","KSDM":"1231","MRBZ":0,"KSMC":"质控中心"},{"itemName":null,"YGDM":"[isSurgery]","KSDM":"1029","MRBZ":0,"KSMC":"手术室"}]
         * SessionId : 5f7c52f3-5ea2-4b7c-a869-7cf98b568afa
         */

        private LonginUserBean LonginUser;
        private TimeVoBean TimeVo;
        private String SessionId;
        private List<AreasBean> Areas;

        public LonginUserBean getLonginUser() {
            return LonginUser;
        }

        public void setLonginUser(LonginUserBean LonginUser) {
            this.LonginUser = LonginUser;
        }

        public TimeVoBean getTimeVo() {
            return TimeVo;
        }

        public void setTimeVo(TimeVoBean TimeVo) {
            this.TimeVo = TimeVo;
        }

        public String getSessionId() {
            return SessionId;
        }

        public void setSessionId(String SessionId) {
            this.SessionId = SessionId;
        }

        public List<AreasBean> getAreas() {
            return Areas;
        }

        public void setAreas(List<AreasBean> Areas) {
            this.Areas = Areas;
        }

        public static class LonginUserBean {
            /**
             * YHID : 2000002
             * YHXM : 王小王
             * MRBZ : 0
             * JGID : 1
             * YHDM : 5820
             * YHXK : null
             */

            private String YHID;
            private String YHXM;
            private int MRBZ;
            private String JGID;
            private String YHDM;
            private Object YHXK;

            public String getYHID() {
                return YHID;
            }

            public void setYHID(String YHID) {
                this.YHID = YHID;
            }

            public String getYHXM() {
                return YHXM;
            }

            public void setYHXM(String YHXM) {
                this.YHXM = YHXM;
            }

            public int getMRBZ() {
                return MRBZ;
            }

            public void setMRBZ(int MRBZ) {
                this.MRBZ = MRBZ;
            }

            public String getJGID() {
                return JGID;
            }

            public void setJGID(String JGID) {
                this.JGID = JGID;
            }

            public String getYHDM() {
                return YHDM;
            }

            public void setYHDM(String YHDM) {
                this.YHDM = YHDM;
            }

            public Object getYHXK() {
                return YHXK;
            }

            public void setYHXK(Object YHXK) {
                this.YHXK = YHXK;
            }
        }

        public static class TimeVoBean {
            /**
             * itemName : null
             * Time : 2017-07-17 18:17:31
             */

            private Object itemName;
            private String Time;

            public Object getItemName() {
                return itemName;
            }

            public void setItemName(Object itemName) {
                this.itemName = itemName;
            }

            public String getTime() {
                return Time;
            }

            public void setTime(String Time) {
                this.Time = Time;
            }
        }

        public static class AreasBean {
            /**
             * itemName : null
             * YGDM : 2000002
             * KSDM : 1031
             * MRBZ : 0
             * KSMC : 一病区
             */

            private Object itemName;
            private String YGDM;
            private String KSDM;
            private int MRBZ;
            private String KSMC;

            public Object getItemName() {
                return itemName;
            }

            public void setItemName(Object itemName) {
                this.itemName = itemName;
            }

            public String getYGDM() {
                return YGDM;
            }

            public void setYGDM(String YGDM) {
                this.YGDM = YGDM;
            }

            public String getKSDM() {
                return KSDM;
            }

            public void setKSDM(String KSDM) {
                this.KSDM = KSDM;
            }

            public int getMRBZ() {
                return MRBZ;
            }

            public void setMRBZ(int MRBZ) {
                this.MRBZ = MRBZ;
            }

            public String getKSMC() {
                return KSMC;
            }

            public void setKSMC(String KSMC) {
                this.KSMC = KSMC;
            }
        }
}
