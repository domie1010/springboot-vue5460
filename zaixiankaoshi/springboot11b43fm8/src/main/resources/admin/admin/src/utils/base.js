const base = {
    get() {
        return {
            url : "http://localhost:8080/springboot11b43fm8/",
            name: "springboot11b43fm8",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springboot11b43fm8/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "在线考试系统"
        } 
    }
}
export default base
