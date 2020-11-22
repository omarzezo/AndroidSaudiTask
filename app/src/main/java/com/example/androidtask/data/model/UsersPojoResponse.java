package com.example.androidtask.data.model;

import java.io.Serializable;
import java.util.List;

public class UsersPojoResponse {

    /**
     * status : true
     * message : null
     * data : {"users":[{"name":"User-11","image":"http://loremflickr.com/300/300?random=11","items":["http://loremflickr.com/800/800?random=18","http://loremflickr.com/800/800?random=3","http://loremflickr.com/800/800?random=34"]},{"name":"User-12","image":"http://loremflickr.com/300/300?random=12","items":["http://loremflickr.com/800/800?random=24","http://loremflickr.com/800/800?random=21","http://loremflickr.com/800/800?random=42"]},{"name":"User-13","image":"http://loremflickr.com/300/300?random=13","items":["http://loremflickr.com/800/800?random=5","http://loremflickr.com/800/800?random=42","http://loremflickr.com/800/800?random=3","http://loremflickr.com/800/800?random=48","http://loremflickr.com/800/800?random=36"]},{"name":"User-14","image":"http://loremflickr.com/300/300?random=14","items":["http://loremflickr.com/800/800?random=11","http://loremflickr.com/800/800?random=27","http://loremflickr.com/800/800?random=49","http://loremflickr.com/800/800?random=24","http://loremflickr.com/800/800?random=39"]},{"name":"User-15","image":"http://loremflickr.com/300/300?random=15","items":["http://loremflickr.com/800/800?random=28"]},{"name":"User-16","image":"http://loremflickr.com/300/300?random=16","items":["http://loremflickr.com/800/800?random=9","http://loremflickr.com/800/800?random=37","http://loremflickr.com/800/800?random=29","http://loremflickr.com/800/800?random=44","http://loremflickr.com/800/800?random=5"]},{"name":"User-17","image":"http://loremflickr.com/300/300?random=17","items":["http://loremflickr.com/800/800?random=26","http://loremflickr.com/800/800?random=24","http://loremflickr.com/800/800?random=7"]},{"name":"User-18","image":"http://loremflickr.com/300/300?random=18","items":["http://loremflickr.com/800/800?random=19","http://loremflickr.com/800/800?random=22","http://loremflickr.com/800/800?random=47","http://loremflickr.com/800/800?random=48"]},{"name":"User-19","image":"http://loremflickr.com/300/300?random=19","items":["http://loremflickr.com/800/800?random=22"]},{"name":"User-20","image":"http://loremflickr.com/300/300?random=20","items":["http://loremflickr.com/800/800?random=10","http://loremflickr.com/800/800?random=20","http://loremflickr.com/800/800?random=8","http://loremflickr.com/800/800?random=36","http://loremflickr.com/800/800?random=9"]}],"has_more":true}
     */

    private boolean status;
    private Object message;
    private DataBean data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * users : [{"name":"User-11","image":"http://loremflickr.com/300/300?random=11","items":["http://loremflickr.com/800/800?random=18","http://loremflickr.com/800/800?random=3","http://loremflickr.com/800/800?random=34"]},{"name":"User-12","image":"http://loremflickr.com/300/300?random=12","items":["http://loremflickr.com/800/800?random=24","http://loremflickr.com/800/800?random=21","http://loremflickr.com/800/800?random=42"]},{"name":"User-13","image":"http://loremflickr.com/300/300?random=13","items":["http://loremflickr.com/800/800?random=5","http://loremflickr.com/800/800?random=42","http://loremflickr.com/800/800?random=3","http://loremflickr.com/800/800?random=48","http://loremflickr.com/800/800?random=36"]},{"name":"User-14","image":"http://loremflickr.com/300/300?random=14","items":["http://loremflickr.com/800/800?random=11","http://loremflickr.com/800/800?random=27","http://loremflickr.com/800/800?random=49","http://loremflickr.com/800/800?random=24","http://loremflickr.com/800/800?random=39"]},{"name":"User-15","image":"http://loremflickr.com/300/300?random=15","items":["http://loremflickr.com/800/800?random=28"]},{"name":"User-16","image":"http://loremflickr.com/300/300?random=16","items":["http://loremflickr.com/800/800?random=9","http://loremflickr.com/800/800?random=37","http://loremflickr.com/800/800?random=29","http://loremflickr.com/800/800?random=44","http://loremflickr.com/800/800?random=5"]},{"name":"User-17","image":"http://loremflickr.com/300/300?random=17","items":["http://loremflickr.com/800/800?random=26","http://loremflickr.com/800/800?random=24","http://loremflickr.com/800/800?random=7"]},{"name":"User-18","image":"http://loremflickr.com/300/300?random=18","items":["http://loremflickr.com/800/800?random=19","http://loremflickr.com/800/800?random=22","http://loremflickr.com/800/800?random=47","http://loremflickr.com/800/800?random=48"]},{"name":"User-19","image":"http://loremflickr.com/300/300?random=19","items":["http://loremflickr.com/800/800?random=22"]},{"name":"User-20","image":"http://loremflickr.com/300/300?random=20","items":["http://loremflickr.com/800/800?random=10","http://loremflickr.com/800/800?random=20","http://loremflickr.com/800/800?random=8","http://loremflickr.com/800/800?random=36","http://loremflickr.com/800/800?random=9"]}]
         * has_more : true
         */

        private boolean has_more;
        private List<UsersBean> users;

        public boolean isHas_more() {
            return has_more;
        }

        public void setHas_more(boolean has_more) {
            this.has_more = has_more;
        }

        public List<UsersBean> getUsers() {
            return users;
        }

        public void setUsers(List<UsersBean> users) {
            this.users = users;
        }

        public static class UsersBean implements Serializable {
            /**
             * name : User-11
             * image : http://loremflickr.com/300/300?random=11
             * items : ["http://loremflickr.com/800/800?random=18","http://loremflickr.com/800/800?random=3","http://loremflickr.com/800/800?random=34"]
             */

            private String name;
            private String image;
            private List<String> items;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public List<String> getItems() {
                return items;
            }

            public void setItems(List<String> items) {
                this.items = items;
            }
        }
    }
}
