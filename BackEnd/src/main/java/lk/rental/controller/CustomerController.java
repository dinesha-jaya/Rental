package lk.rental.controller;

import lk.rental.dto.CustomerDTO;
import lk.rental.dto.RentSummaryDTO;
import lk.rental.dto.UserDTO;
import lk.rental.service.CustomerService;
import lk.rental.service.UserService;
import lk.rental.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseUtil saveCustomer(@RequestBody CustomerDTO customerDTO) {
        UserDTO userDTO = customerService.addCustomer(customerDTO);
        return new ResponseUtil("200", " Added.!", userDTO);
    }

//    @GetMapping
//    public ResponseUtil getAllCustomers(){
//        ArrayList<CustomerDTO> allCustomers = customerService.getAllCustomers();
//        return new ResponseUtil("200", " Success.!", allCustomers);
//    }

//    @PostMapping("/cookie")
//    public ResponseUtil setOTP(@RequestBody UserDTO credentialDTO) {
////        System.out.println(credentialDTO);
//
//        String password = userService.getPasswordByUsername(credentialDTO.getUsername());
//
////        System.out.println(password);
//
//        credentialDTO.setPassword(password);
//
//        return new ResponseUtil("200", " Added.!", credentialDTO);
//    }


//    @GetMapping("/cookie")
//    public void setOneTimePasswordCookie(@CookieValue(value = "username", defaultValue = "guest") String username, HttpServletResponse response, HttpServletRequest request) {
//        System.out.println(username);
//
//        String password = userService.getPasswordByUsername(username);
//
//        System.out.println(password);
//
//        Cookie cookie = new Cookie("otp", password);
//        response.addCookie(cookie);
//
//        Cookie[] cookies = request.getCookies();
//        String cookieString = "";
//        if (cookies != null) {
//            cookieString = Arrays.stream(cookies)
//                    .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
//        }
//
//        System.out.println(cookieString);
//
//
//    }

//    @GetMapping("/all-cookies")
//    public String readAllCookies(HttpServletRequest request) {
//
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            return Arrays.stream(cookies)
//                    .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
//        }
//
//        return "No cookies";
//    }
}
