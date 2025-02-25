// package com.openclassrooms.api.dto;

// import java.time.LocalDateTime;
// import java.util.List;

// public class UserWithSubscriptionsDTO extends UserDTO {
//     private List<SubjectDTO> subscriptions;
    
//     public UserWithSubscriptionsDTO(Long id, String email, String name, String role, 
//                                   LocalDateTime created_at, LocalDateTime updated_at,
//                                   List<SubjectDTO> subscriptions) {
//         super(id, email, name, role, created_at, updated_at);
//         this.subscriptions = subscriptions;
//     }
    
//     public List<SubjectDTO> getSubscriptions() {
//         return subscriptions;
//     }
    
//     public void setSubscriptions(List<SubjectDTO> subscriptions) {
//         this.subscriptions = subscriptions;
//     }
// }