package com.newStudy.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author shkstart
 * @create 2020-02-16-17:51
 */
@Controller
@RequestMapping("/alpha")
public class AlphaController {
    //底层如何获得请求对象和响应对象
    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //可以通过response对象直接向浏览器输出数据，所以就不需要（依赖）返回值
        //DispatcherServlet调用改方法时会自动将接收到request或者response给到形参。
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        //请求行是key-value结构，得到一个Enumeration迭代器
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }
        System.out.println(request.getParameter("code"));//浏览器?code=123,则123会被接收到。
        // 返回响应数据
        response.setContentType("text/html;charset=utf-8");
        try (PrintWriter writer = response.getWriter();) {      //response对象中封装了输出流
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //GET请求如何处理比如需要查询所有学生，查询的路径是/student?current=1&limit=20
    @RequestMapping(path="/student",method = RequestMethod.GET)//访问路径,指定请求方式
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
        System.out.println(current);//看看是否能得到这个参数
        System.out.println(limit);
        return "some students";
    }
    //GET请求如何处理比如需要查询一个学生
    // /student/123
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {
        System.out.println(id);
        return "a student";
    }
    // POST请求
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return "success";
    }
    // 响应HTML数据
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
        public ModelAndView getTeacher() {               //返回的ModelAndView给到前端控制器
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "张三");
        mav.addObject("age", 30);
        mav.setViewName("/demo/view");                   //template/demo/view注意：view默认是html文件
        return mav;
    }
    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model) {                          //前端控制器会自动赋值给参数model
        model.addAttribute("name", "北京大学");               //前端控制器持有model的引用，
        model.addAttribute("age", 80);                        // 我们存数据的时候前端控制器也能得到
        return "/demo/view";                                        //将view返回给前端控制器
    }
    //返回一个员工
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody				//不加此注解会返回HTML，加上则可以返回字符串
    public Map<String, Object> getEmp() {
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary", 8000.00);
        return emp;
    }
    //前端控制器监测到@ResponseBody和map的返回值类型后会将返回值转化为json字符串。
//查询所有员工
    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps() {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary", 8000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "李四");
        emp.put("age", 24);
        emp.put("salary", 9000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "王五");
        emp.put("age", 25);
        emp.put("salary", 10000.00);
        list.add(emp);

        return list;
    }
}
