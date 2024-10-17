package com.hamitmizrak.thy_springboot_redis.annotation;

import com.hamitmizrak.thy_springboot_redis.data.repository.IAddressRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

// LOMBOK
@RequiredArgsConstructor

// Unique Address QR CODE Annotation
public class UniqueAddressQRCodeValidation implements ConstraintValidator<UniqueAddressQRCode, String> {

    // INJECTION
    private final IAddressRepository iAddressRepository;

    // QR kodu
    @Override
    public boolean isValid(String qrCode, ConstraintValidatorContext constraintValidatorContext) {
        // 1.YOL
        // AddressEntity isAddressQrCodeEntity=iAddressRepository.findByAddressQrCode(qrCode).orElseThrow(()-> new _404_NotFoundException(qrCode+" bulunmadı"));

        // 2.YOL
        boolean isAddressQrCodeOptional = iAddressRepository.findByAddressDetails_AddressQrCode(qrCode).isPresent();
        System.out.println(iAddressRepository.findByAddressDetails_AddressQrCode(qrCode).isPresent());
        // Aynı QR Code varsa return false olsun
        if (isAddressQrCodeOptional){
            System.out.println("Aynı isimde QR Code var "+isAddressQrCodeOptional);
            return false;
        } else {
            System.out.println("Harika bu QR Code kullanabilirsiniz. "+isAddressQrCodeOptional);
            return true;
        } // end else
    } //end isValid
} //end UniqueAddressQRCodeValidation
