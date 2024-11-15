public class javaBean {
    private int age;
    private String name;
    private int sex;
    private float height;

    public javaBean() {
    }

    public javaBean(int age, String name, int sex, float height) {
        this.age = age;
        this.name = name;
        this.sex = sex;
        this.height = height;
    }

    /**
     * 获取
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return sex
     */
    public int getSex() {
        return sex;
    }

    /**
     * 设置
     * @param sex
     */
    public void setSex(int sex) {
        this.sex = sex;
    }

    /**
     * 获取
     * @return height
     */
    public float getHeight() {
        return height;
    }

    /**
     * 设置
     * @param height
     */
    public void setHeight(float height) {
        this.height = height;
    }

}
