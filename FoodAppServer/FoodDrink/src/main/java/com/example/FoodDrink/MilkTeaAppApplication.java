package com.example.FoodDrink;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@RequiredArgsConstructor
public class MilkTeaAppApplication {
//    private final NotificationService notificationService;
	public static void main(String[] args) {
		SpringApplication.run(MilkTeaAppApplication.class, args);
	}

//    @Bean
//    CommandLineRunner runner(){
//        return args -> {
//            // You can use the notificationService here if needed
//            NotificationDTO notificationDTO = new NotificationDTO();
//            notificationDTO.setRecipient("nvquang333@gmail.com");
//            notificationDTO.setSubject("Test Subject");
//            notificationDTO.setBody("This is a test notification body.");
//            notificationDTO.setHtml(false);
//            notificationDTO.setType(NotificationType.EMAIL);
//            notificationService.sendEmail(notificationDTO);
//        };
//    }
}
