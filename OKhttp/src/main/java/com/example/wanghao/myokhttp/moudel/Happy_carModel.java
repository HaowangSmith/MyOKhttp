package com.example.wanghao.myokhttp.moudel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WangHao
 *
 * @ 创建时间 2016/10/21  20:26
 */

public class Happy_carModel implements Serializable {

    /**
     * return_code : 0
     * data : {"shops":[{"id":"21","mainpic":"/Public/upload/pic/cb8555d00065376c0c1113bb0e2d7704.jpg","name":"温州玛奇朵婚纱摄影","addr":"温州市下吕浦雄鹰7幢106","guanzhu_num":"2253","fanwei":"婚礼策划"},{"id":"10","mainpic":"/Public/upload/pic/cb8555d00065376c0c1113bb0e2d7704.jpg","name":"百福珠宝","addr":"温州市鹿城区","guanzhu_num":"2253","fanwei":"婚庆用品"}]}
     */

    private int return_code;
    private DataBean data;

    public int getReturn_code() {
        return return_code;
    }

    public void setReturn_code(int return_code) {
        this.return_code = return_code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 21
         * mainpic : /Public/upload/pic/cb8555d00065376c0c1113bb0e2d7704.jpg
         * name : 温州玛奇朵婚纱摄影
         * addr : 温州市下吕浦雄鹰7幢106
         * guanzhu_num : 2253
         * fanwei : 婚礼策划
         */

        private List<ShopsBean> shops;

        public List<ShopsBean> getShops() {
            return shops;
        }

        public void setShops(List<ShopsBean> shops) {
            this.shops = shops;
        }

        public static class ShopsBean {
            private String id;
            private String mainpic;
            private String name;
            private String addr;
            private String guanzhu_num;
            private String fanwei;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMainpic() {
                return mainpic;
            }

            public void setMainpic(String mainpic) {
                this.mainpic = mainpic;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public String getGuanzhu_num() {
                return guanzhu_num;
            }

            public void setGuanzhu_num(String guanzhu_num) {
                this.guanzhu_num = guanzhu_num;
            }

            public String getFanwei() {
                return fanwei;
            }

            public void setFanwei(String fanwei) {
                this.fanwei = fanwei;
            }
        }
    }
}
