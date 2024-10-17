#!/bin/bash

echo -e "Spring Boot & Redis & Docker kurulumları başlıyor..."

# User Variable
PROFILES="Profile Seçimi"


# H2DB (Development)
# spring.profiles.active=dev
# mvn spring-boot:run -Dspring.profiles.active=dev

# MYSQL (Production)
# spring.profiles.active=prod
# mvn spring-boot:run -Dspring.profiles.active=prod

# POSTGRESQL (Test)
# spring.profiles.active=test
# mvn spring-boot:run -Dspring.profiles.active=test

###################################################################
###################################################################
# Updated
springboot_profiles_chooise() {
    sleep 2
    echo -e "\n###### ${PROFILES} ######  "

    # Güncelleme Tercihi
    echo -e "Profile İçin Seçim Yapınız\n1-)Development\n2-)Production\n3-)Test\n4-)Çıkış "
    read chooise

    # Girilen sayıya göre tercih
    case $chooise in
        1)
            read -p "Development Profili için seçmek istiyor musunuz ? e/h " developmentProfilesResult
            if [[ developmentProfilesResult == "e" || developmentProfilesResult == "E" ]]; then
                echo -e "Profile Development  Başladı ... "

                 # Geriye Say
                # sudo ./countdown.sh

                # Profiles Dev
                mvn spring-boot:run -Dspring.profiles.active=dev
            else
                echo -e "Dev Profiles Seçilmedi"
            fi
            ;;
        2)
            read -p "Production Profili için seçmek istiyor musunuz ? e/h " productionProfileResult
            if [[ $productionProfileResult == "e" || $productionProfileResult == "E" ]]; then
                echo -e "Profile Production  Başladı  ..."

                # Geriye Say
                # sudo ./countdown.sh

                 # Profiles Prod
                mvn spring-boot:run -Dspring.profiles.active=prod

            else
                echo -e "Prod Profiles Seçilmedi... "
            fi
            ;;
        3)
            read -p "Test Profili için seçmek istiyor musunuz ?e/h " testProfileResult
            if [[ $testProfileResult == "e" || $testProfileResult == "E" ]]; then
                echo -e "Profile Production  Başladı  ... "

                 # Geriye Say
                # sudo ./countdown.sh

               # Profiles Test
                mvn spring-boot:run -Dspring.profiles.active=test

            else
                echo -e "Kernel Güncellemesi Yapılmadı... "
            fi
            ;;
        *)
            echo -e "Lütfen sadece size belirtilen seçeneği seçiniz"
            ;;
    esac
}
springboot_profiles_chooise
