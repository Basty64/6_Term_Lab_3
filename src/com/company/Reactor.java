package com.company; //подключили GSON

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TYPE")
public class Reactor {

        private String classs;
        private Double burnup;
        private Double kpd;
        private Double enrichment;
        private Double termal_capacity;
        private Double electrical_capacity;
        private Double life_time;
        private Double first_load;
        private String sourse; //строка показаыват из какого файла(расширения) зачитываем

        public Reactor(String c, Double b, Double k, Double e, Double tc, Double ec, Double lt, Double fl, String s) {
                this.classs = c;
                this.burnup = b;
                this.kpd = k;
                this.enrichment = e;
                this.termal_capacity = tc;
                this.electrical_capacity = ec;
                this.life_time = lt;
                this.first_load = fl;
                this.sourse = s;
        }

        public String getClasss() {
                return classs;
        }

        public Double getBurnup() {
                return burnup;
        }

        public Double getKpd() {
                return kpd;
        }

        public Double getEnrichment() {
                return enrichment;
        }

        public Double getTermal_capacity() {
                return termal_capacity;
        }

        public Double getElectrical_capacity() {
                return electrical_capacity;
        }

        public Double getLife_time() {
                return life_time;
        }

        public Double getFirst_load() {
                return first_load;
        }

        public String getSourse() {
                return sourse;
        }
}
