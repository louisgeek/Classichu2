package com.classichu.classichu2.logic.patient.bean;

import java.util.List;

/**
 * Created by Classichu on 2017-7-21.
 */

public class PatientInfoBean {

        /**
         * patient : {"itemName":null,"ZYH":"5","ZYHM":"00000015","BRXM":"蔡文姬","BRXB":2,"CSNY":"1995-01-01 00:00:00","BRCH":"3005","HLJB":1,"BRBQ":"1033","BRKS":"1009","ZZYS":"1188914","BRXZ":"1000","JLLX":"1","RYRQ":"2017-05-11 14:38:00","BRNL":null,"XZMC":"自费","KSMC":"血液内科","YSMC":"马朝学"}
         * expenseTotal : {"itemName":null,"ZJJE":"56.4200","ZFJE":"56.4200","JKJE":"10000.00","FYYE":"9943.58"}
         * diagnose :
         * allergicDrugs : []
         * states : []
         */

        private PatientBean patient;
        private ExpenseTotalBean expenseTotal;
        private String diagnose;
        private List<AllergicDrugBean> allergicDrugs;
        private List<StateBean> states;

        public PatientBean getPatient() {
            return patient;
        }

        public void setPatient(PatientBean patient) {
            this.patient = patient;
        }

        public ExpenseTotalBean getExpenseTotal() {
            return expenseTotal;
        }

        public void setExpenseTotal(ExpenseTotalBean expenseTotal) {
            this.expenseTotal = expenseTotal;
        }

        public String getDiagnose() {
            return diagnose;
        }

        public void setDiagnose(String diagnose) {
            this.diagnose = diagnose;
        }

        public List<AllergicDrugBean> getAllergicDrugs() {
            return allergicDrugs;
        }

        public void setAllergicDrugs(List<AllergicDrugBean> allergicDrugs) {
            this.allergicDrugs = allergicDrugs;
        }

        public List<StateBean> getStates() {
            return states;
        }

        public void setStates(List<StateBean> states) {
            this.states = states;
        }

    public static class StateBean {
        private String zttb;// 状态图标
        private String ztnr;// 状态内容
        private int ztlx;// 状态类型
        private String ztmc;// 状态名称
        public String getZttb() {
            return zttb;
        }

        public void setZttb(String zttb) {
            this.zttb = zttb;
        }

        public String getZtnr() {
            return ztnr;
        }

        public void setZtnr(String ztnr) {
            this.ztnr = ztnr;
        }

        public int getZtlx() {
            return ztlx;
        }

        public void setZtlx(int ztlx) {
            this.ztlx = ztlx;
        }

        public String getZtmc() {
            return ztmc;
        }

        public void setZtmc(String ztmc) {
            this.ztmc = ztmc;
        }

    }

        public static class AllergicDrugBean {
            //过敏药物
            private String YPMC;
            private String GMZZ;

            public String getYPMC() {
                return YPMC;
            }

            public void setYPMC(String YPMC) {
                this.YPMC = YPMC;
            }

            public String getGMZZ() {
                return GMZZ;
            }

            public void setGMZZ(String GMZZ) {
                this.GMZZ = GMZZ;
            }
        }
        public static class PatientBean {
            /**
             * itemName : null
             * ZYH : 5
             * ZYHM : 00000015
             * BRXM : 蔡文姬
             * BRXB : 2
             * CSNY : 1995-01-01 00:00:00
             * BRCH : 3005
             * HLJB : 1
             * BRBQ : 1033
             * BRKS : 1009
             * ZZYS : 1188914
             * BRXZ : 1000
             * JLLX : 1
             * RYRQ : 2017-05-11 14:38:00
             * BRNL : null
             * XZMC : 自费
             * KSMC : 血液内科
             * YSMC : 马朝学
             */

            private Object itemName;
            private String ZYH;
            private String ZYHM;
            private String BRXM;
            private int BRXB;
            private String CSNY;
            private String BRCH;
            private int HLJB;
            private String BRBQ;
            private String BRKS;
            private String ZZYS;
            private String BRXZ;
            private String JLLX;
            private String RYRQ;
            private Object BRNL;
            private String XZMC;
            private String KSMC;
            private String YSMC;

            public Object getItemName() {
                return itemName;
            }

            public void setItemName(Object itemName) {
                this.itemName = itemName;
            }

            public String getZYH() {
                return ZYH;
            }

            public void setZYH(String ZYH) {
                this.ZYH = ZYH;
            }

            public String getZYHM() {
                return ZYHM;
            }

            public void setZYHM(String ZYHM) {
                this.ZYHM = ZYHM;
            }

            public String getBRXM() {
                return BRXM;
            }

            public void setBRXM(String BRXM) {
                this.BRXM = BRXM;
            }

            public int getBRXB() {
                return BRXB;
            }

            public void setBRXB(int BRXB) {
                this.BRXB = BRXB;
            }

            public String getCSNY() {
                return CSNY;
            }

            public void setCSNY(String CSNY) {
                this.CSNY = CSNY;
            }

            public String getBRCH() {
                return BRCH;
            }

            public void setBRCH(String BRCH) {
                this.BRCH = BRCH;
            }

            public int getHLJB() {
                return HLJB;
            }

            public void setHLJB(int HLJB) {
                this.HLJB = HLJB;
            }

            public String getBRBQ() {
                return BRBQ;
            }

            public void setBRBQ(String BRBQ) {
                this.BRBQ = BRBQ;
            }

            public String getBRKS() {
                return BRKS;
            }

            public void setBRKS(String BRKS) {
                this.BRKS = BRKS;
            }

            public String getZZYS() {
                return ZZYS;
            }

            public void setZZYS(String ZZYS) {
                this.ZZYS = ZZYS;
            }

            public String getBRXZ() {
                return BRXZ;
            }

            public void setBRXZ(String BRXZ) {
                this.BRXZ = BRXZ;
            }

            public String getJLLX() {
                return JLLX;
            }

            public void setJLLX(String JLLX) {
                this.JLLX = JLLX;
            }

            public String getRYRQ() {
                return RYRQ;
            }

            public void setRYRQ(String RYRQ) {
                this.RYRQ = RYRQ;
            }

            public Object getBRNL() {
                return BRNL;
            }

            public void setBRNL(Object BRNL) {
                this.BRNL = BRNL;
            }

            public String getXZMC() {
                return XZMC;
            }

            public void setXZMC(String XZMC) {
                this.XZMC = XZMC;
            }

            public String getKSMC() {
                return KSMC;
            }

            public void setKSMC(String KSMC) {
                this.KSMC = KSMC;
            }

            public String getYSMC() {
                return YSMC;
            }

            public void setYSMC(String YSMC) {
                this.YSMC = YSMC;
            }
        }

        public static class ExpenseTotalBean {
            /**
             * itemName : null
             * ZJJE : 56.4200
             * ZFJE : 56.4200
             * JKJE : 10000.00
             * FYYE : 9943.58
             */

            private Object itemName;
            private String ZJJE;
            private String ZFJE;
            private String JKJE;
            private String FYYE;

            public Object getItemName() {
                return itemName;
            }

            public void setItemName(Object itemName) {
                this.itemName = itemName;
            }

            public String getZJJE() {
                return ZJJE;
            }

            public void setZJJE(String ZJJE) {
                this.ZJJE = ZJJE;
            }

            public String getZFJE() {
                return ZFJE;
            }

            public void setZFJE(String ZFJE) {
                this.ZFJE = ZFJE;
            }

            public String getJKJE() {
                return JKJE;
            }

            public void setJKJE(String JKJE) {
                this.JKJE = JKJE;
            }

            public String getFYYE() {
                return FYYE;
            }

            public void setFYYE(String FYYE) {
                this.FYYE = FYYE;
            }
        }
}
