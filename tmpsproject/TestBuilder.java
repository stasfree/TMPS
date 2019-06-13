package ua.tmpsproject;

public class TestBuilder {

    public static class Builder {

        private String username;
        private String password;

        public Builder() {
        }

        public Builder specifyUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder specifyPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder build(){
            TestBuilder.Builder loginData = new TestBuilder.Builder();
            loginData.username=this.username;
            loginData.password=this.password;
            return loginData;
        }
    }

    Builder loginData1 = new Builder().specifyUsername("username@contoso.com").specifyPassword("123456").build();
}
