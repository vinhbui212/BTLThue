package com.example.hotrotinhthue.controller;

import com.example.hotrotinhthue.model.NguoiDung;
import com.example.hotrotinhthue.service.NguoiDungService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class TrangChuController {
	
	@Autowired
    private NguoiDungService nguoiDungService;

    @GetMapping({"index", "/"})
    public String index() {
        return "index";
    }
    
    @GetMapping("error")
	public String error() {
		return "error";
	}
    
    @GetMapping("dang-ki")
    public String dangKi(Model model) {
    	model.addAttribute("nguoiDung", new NguoiDung());
    	return "dang-ki";
    }
    
    @PostMapping("dang-ki")
    public String dangKi(@Valid @ModelAttribute("nguoiDung") NguoiDung nguoiDung, BindingResult result, String nhapLaiMatKhau, Model model) {
    	if(nguoiDungService.addNguoiDung(nguoiDung, result, nhapLaiMatKhau)==null) {
    		model.addAttribute("nguoiDung", nguoiDung);
    		return "dang-ki";
    	}
    	model.addAttribute("message", "Đăng kí thành công, mời đăng nhập!");
    	return "dang-nhap";
    }
    
    @GetMapping("dang-nhap")
    public String dangNhap() {
    	return "dang-nhap";
    }

    @GetMapping("dang-nhap-admin")
    public String dangNhapadmin() {
        return "dang-nhap-admin";
    }

    @PostMapping("dang-nhap-admin")
    public String dangNhapPost(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        // Your authentication logic goes here
        // You can use Spring Security or custom authentication logic to authenticate the user

        // For example, you can check the username and password against your database
        if (username.equals("admin") && password.equals("1234")) {
            // Authentication successful, redirect to a success page or perform other actions
            return "admin"; // Redirect to dashboard page after successful login
        } else {
            // Authentication failed, add an error message to the model and return to the login page
            model.addAttribute("message", "Tài khoản hoặc mật khẩu không chính xác!");
            return "dang-nhap"; // Return to the login page
        }
    }
}
