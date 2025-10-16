    package com.algohire.backend.dto.request;

    import jakarta.validation.constraints.Email;
    import jakarta.validation.constraints.NotBlank;
    import lombok.Data;


    @Data
    public class CompanyRequstDto {
           @NotBlank(message=" company name can't be empty")
        private String name;

        @NotBlank(message="email required")
        @Email
        private String email;

        @NotBlank private String address;
        @NotBlank private String phone;
        private String website;
        private String about;
        private String logoUrl;
        private String companySize;
    }
