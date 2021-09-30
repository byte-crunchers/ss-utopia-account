package com.ssutopia.financial.accountService.bootstrap;

import com.ssutopia.financial.accountService.entity.AccountTypes;
import com.ssutopia.financial.accountService.entity.Accounts;
import com.ssutopia.financial.accountService.entity.Cards;
import com.ssutopia.financial.accountService.entity.Users;
import com.ssutopia.financial.accountService.repository.AccountTypeRepository;
import com.ssutopia.financial.accountService.repository.AccountsRepository;
import com.ssutopia.financial.accountService.repository.CardsRepository;
import com.ssutopia.financial.accountService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class H2DataBootstrap implements CommandLineRunner {
    private final AccountTypeRepository accountTypeRepository;
    private final AccountsRepository accountsRepository;
    private final CardsRepository cardsRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();;
    String sDate1="12/2025";
    String expDate = "11/25"; // for example
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");


    @Override
    public void run(String... args) throws Exception {
        if(accountTypeRepository.count()==0){

            loadUser();
            loadAllCreditAccounts();
            loadAllDebitAccounts();
        }
    }


    private void loadUser(){

        var User1 = Users.builder()
                .id(1L)
                .username("dan")
                .first_name("Jim")
                .last_name("Wo")
                .address( "529-5103 Hendrerit. Rd.")
                .email("smoothstack@email.com")
                .is_admin(false)
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .build();
        userRepository.save(User1);

        var User2 = Users.builder()
                .id(2L)
                .username("admin")
                .first_name("Tim")
                .last_name("lo")
                .address("Ap #281-2005 Tristique St.")
                .email("smoothstack@email.com")
                .is_admin(true)
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .permissions("ACCESS_TEST1,ACCESS_TEST2")
                .build();

        userRepository.save(User2);

        var User3 = Users.builder()
                .id(3L)
                .username("manager")
                .first_name("Amy")
                .last_name("To")
                .email("smoothstack@email.com")
                .is_admin(false)
                .address("436-6810 Nunc Rd.")
                .password(passwordEncoder.encode("manager123"))
                .roles("MANAGER")
                .permissions("ACCESS_TEST1")
                .build();

        userRepository.save(User3);


    }

    private void loadAllDebitAccounts() throws ParseException {
        var AccountType12 = AccountTypes.builder()
                .id(12L)
                .foodie_pts(0.0f)
                .savings_interest(0.01f)
                .cashBack(0.00f)
                .annual_fee(50)
                .late_fee(00.00f)
                .build();

        var User12 = Users.builder()
                .id(15L)
                .username("Lisandra")
                .first_name("Boyle")
                .last_name("Will")
                .address("338-1607 Egestas. St.")
                .email("smoothstack@email.com")
                .is_admin(false)
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .build();

        var Accounts12 = Accounts.builder()
                .id(12L)
                .balance(9009.00f)
                .accountTypes(AccountType12)
                .active(true)
                .approved(true)
                .confirmed(true)
                .debt_interest(0.04f)
                .limit(10000)

                .users(User12)
                .build();

        var Card12 = Cards.builder()
                .id(12L)
                .card_num("999923393855048")
                .exp_date(new SimpleDateFormat("MM/yyyy").parse(sDate1))
                .accounts(Accounts12)
                .cvc1(823)
                .cvc2(756)
                .pin(567)
                .build();
        accountTypeRepository.save(AccountType12);
        userRepository.save(User12);
        accountsRepository.save(Accounts12);
        cardsRepository.save(Card12);


        var AccountType13 = AccountTypes.builder()
                .id(13L)
                .foodie_pts(0.00f)
                .savings_interest(0.02f)
                .cashBack(0.0f)
                .annual_fee(50)
                .late_fee(0.00f)
                .build();

        var User13 = Users.builder()
                .id(16L)
                .username("GlassDoor")
                .first_name("Summers")
                .last_name("Mitt")
                .email("smoothstack@email.com")
                .is_admin(false)
                .address("831-5992 Dui, Rd.")
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .build();

        var Accounts13 = Accounts.builder()
                .id(13L)
                .balance(3230.00f)
                .accountTypes(AccountType13)
                .active(true)
                .approved(true)
                .confirmed(true)
                .debt_interest(0.03f)
                .limit(20000)

                .users(User13)
                .build();


        var Card13 = Cards.builder()
                .id(13L)
                .card_num("998923582648170")
                .exp_date(new SimpleDateFormat("MM/yyyy").parse(sDate1))
                .accounts(Accounts13)
                .pin(123)
                .cvc2(987)
                .cvc1(346)
                .build();
        accountTypeRepository.save(AccountType13);
        userRepository.save(User13);
        accountsRepository.save(Accounts13);
        cardsRepository.save(Card13);

        var AccountType14 = AccountTypes.builder()
                .id(14L)
                .foodie_pts(0.00f)
                .savings_interest(0.01f)
                .cashBack(0.00f)
                .annual_fee(60)
                .late_fee(0.00f)
                .build();


        var User14 = Users.builder()
                .id(17L)
                .username("PenWindows")
                .first_name("Ulla")
                .last_name("English")
                .address("Ap #475-7333 Aenean Avenue")
                .email("smoothstack@email.com")
                .is_admin(false)
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .build();


        var Accounts14 = Accounts.builder()
                .id(14L)
                .balance(9230.00f)
                .accountTypes(AccountType14)
                .active(true)
                .approved(true)
                .confirmed(true)
                .debt_interest(0.02f)
                .limit(90000)
                .users(User14)

                .build();


        var Card14 = Cards.builder()
                .id(14L)
                .card_num("999923631274614")
                .exp_date(new SimpleDateFormat("MM/yyyy").parse(sDate1))
                .accounts(Accounts14)
                .pin(963)
                .cvc2(447)
                .cvc1(346)
                .build();
        accountTypeRepository.save(AccountType14);
        userRepository.save(User14);
        accountsRepository.save(Accounts14);
        cardsRepository.save(Card14);

        var AccountType15 = AccountTypes.builder()
                .id(15L)
                .foodie_pts(0.0f)
                .savings_interest(0.01f)
                .cashBack(0.0f)
                .annual_fee(50)

                .late_fee(0.00f)
                .build();

        var User15 = Users.builder()
                .id(18L)
                .username("NoteBook")
                .first_name("Axel")
                .last_name("York")
                .address("Ap #854-9769 Quam Rd.")
                .email("smoothstack@email.com")
                .is_admin(false)
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .build();



        var Accounts15 = Accounts.builder()
                .id(15L)
                .balance(9230.00f)
                .accountTypes(AccountType15)
                .active(true)
                .approved(true)
                .confirmed(true)
                .debt_interest(0.0f)
                .limit(190000)

                .users(User15)
                .build();



        var Card15 = Cards.builder()
                .id(15L)
                .card_num("888923738762800")
                .exp_date(new SimpleDateFormat("MM/yyyy").parse(sDate1))
                .accounts(Accounts15)
                .pin(213)
                .cvc2(447)
                .cvc1(326)
                .build();

        accountTypeRepository.save(AccountType15);
        userRepository.save(User15);
        accountsRepository.save(Accounts15);
        cardsRepository.save(Card15);



        var AccountType16 = AccountTypes.builder()
                .id(16L)
                .foodie_pts(0.00f)
                .savings_interest(0.0f)
                .cashBack(0.0f)
                .annual_fee(0.00f)

                .late_fee(0.00f)
                .build();



        var User16 = Users.builder()
                .id(19L)
                .username("Perpat467")
                .first_name("Hanna")
                .last_name("Conrad")
                .email("smoothstack@email.com")
                .is_admin(false)
                .address("P.O. Box 769, 2359 Vivamus St.")
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .build();


        var Accounts16 = Accounts.builder()
                .id(16L)
                .balance(8230.00f)
                .accountTypes(AccountType16)
                .active(true)
                .approved(true)
                .confirmed(true)
                .debt_interest(0.0f)
                .limit(120000)
                .users(User16)

                .build();


        var Card16 = Cards.builder()
                .id(16L)
                .card_num("5566231168953886")
                .exp_date(new SimpleDateFormat("MM/yyyy").parse(sDate1))
                .accounts(Accounts16)
                .pin(200)
                .cvc2(447)
                .cvc1(326)
                .build();


        accountTypeRepository.save(AccountType16);
        userRepository.save(User16);
        accountsRepository.save(Accounts16);
        cardsRepository.save(Card16);



        var AccountType17 = AccountTypes.builder()
                .id(17L)
                .foodie_pts(0.0f)
                .savings_interest(0.00f)
                .cashBack(0.0f)
                .annual_fee(0)
                .late_fee(0.00f)
                .build();

        var User17 = Users.builder()
                .id(20L)
                .username("Neck3467")
                .first_name("Oliver")
                .last_name("Frost")
                .email("smoothstack@email.com")
                .is_admin(false)
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .address( "551-8311 Luctus St.")
                .permissions("")
                .build();

        var Accounts17 = Accounts.builder()
                .id(17L)
                .balance(3004f)
                .accountTypes(AccountType17)
                .active(true)
                .approved(true)
                .confirmed(true)
                .debt_interest(0.0f)
                .limit(20000)

                .users(User17)
                .build();

        var Card17 = Cards.builder()
                .id(17L)
                .card_num("441923393855048")
                .exp_date(new SimpleDateFormat("MM/yyyy").parse(sDate1))
                .accounts(Accounts17)
                .cvc1(603)
                .cvc2(456)
                .pin(547)
                .exp_date(new Date())
                .build();
        accountTypeRepository.save(AccountType17);
        userRepository.save(User17);
        accountsRepository.save(Accounts17);
        cardsRepository.save(Card17);


        var AccountType18 = AccountTypes.builder()
                .id(18L)
                .foodie_pts(0.0f)
                .savings_interest(0.01f)
                .cashBack(0.0f)
                .annual_fee(50)
                .late_fee(0.00f)
                .build();

        var User18 = Users.builder()
                .id(21L)
                .username("applestores")
                .first_name("Russell")
                .last_name("Wright")
                .email("smoothstack@email.com")
                .is_admin(false)
                .address("324-1086 Parturient St.")
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .build();

        var Accounts18 = Accounts.builder()
                .id(18L)
                .balance(6300.00f)
                .accountTypes(AccountType18)
                .active(true)
                .approved(true)
                .confirmed(true)
                .debt_interest(0.03f)
                .limit(50000)

                .users(User18)
                .build();


        var Card18 = Cards.builder()
                .id(18L)
                .card_num("231923112648170")
                .exp_date(new SimpleDateFormat("MM/yyyy").parse(sDate1))
                .accounts(Accounts18)
                .pin(123)
                .cvc2(467)
                .cvc1(346)
                .build();
        accountTypeRepository.save(AccountType18);
        userRepository.save(User18);
        accountsRepository.save(Accounts18);
        cardsRepository.save(Card18);

        var AccountType19 = AccountTypes.builder()
                .id(19L)
                .foodie_pts(0.0f)
                .savings_interest(0.01f)
                .cashBack(0.0f)
                .annual_fee(60)
                .late_fee(0.00f)
                .build();


        var User19 = Users.builder()
                .id(22L)
                .username("Dawn")
                .first_name("Garcia")
                .last_name("Chen")
                .address("P.O. Box 430, 2261 Hendrerit Av.")
                .email("smoothstack@email.com")
                .is_admin(false)
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .build();


        var Accounts19 = Accounts.builder()
                .id(19L)
                .balance(6230.00f)
                .accountTypes(AccountType19)
                .active(true)
                .approved(true)
                .confirmed(true)
                .debt_interest(0.02f)
                .limit(70000)
                .users(User19)

                .build();


        var Card19 = Cards.builder()
                .id(19L)
                .card_num("771923631274614")
                .exp_date(new SimpleDateFormat("MM/yyyy").parse(sDate1))
                .accounts(Accounts19)
                .pin(903)
                .cvc2(127)
                .cvc1(306)
                .build();
        accountTypeRepository.save(AccountType19);
        userRepository.save(User19);
        accountsRepository.save(Accounts19);
        cardsRepository.save(Card19);

        var AccountType20 = AccountTypes.builder()
                .id(20L)
                .foodie_pts(0.0f)
                .savings_interest(0.01f)
                .cashBack(0.0f)
                .annual_fee(50)
                .late_fee(0.00f)
                .build();

        var User20 = Users.builder()
                .id(23L)
                .username("swimmingpool")
                .first_name("Johnny")
                .last_name("Moses")
                .email("smoothstack@email.com")
                .is_admin(false)
                .address("Ap #403-759 Quis St.")
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .build();



        var Accounts20 = Accounts.builder()
                .id(20L)
                .balance(20030.00f)
                .accountTypes(AccountType19)
                .active(true)
                .approved(true)
                .confirmed(true)
                .debt_interest(0.01f)
                .limit(180000)

                .users(User20)
                .build();



        var Card20 = Cards.builder()
                .id(20L)
                .card_num("656923738762800")
                .exp_date(new SimpleDateFormat("MM/yyyy").parse(sDate1))
                .accounts(Accounts20)
                .pin(883)
                .cvc2(777)
                .cvc1(326)
                .build();

        accountTypeRepository.save(AccountType20);
        userRepository.save(User20);
        accountsRepository.save(Accounts20);
        cardsRepository.save(Card20);



        var AccountType21 = AccountTypes.builder()
                .id(21L)
                .foodie_pts(0.0f)
                .savings_interest(0.0f)
                .cashBack(0.0f)
                .annual_fee(0.00f)
                .late_fee(0.00f)
                .build();



        var User21 = Users.builder()
                .id(24L)
                .username("Pearreer")
                .first_name("Jenni")
                .last_name("Briar")
                .email("smoothstack@email.com")
                .is_admin(false)
                .address("Ap #876-8402 Mauris Ave")
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .build();


        var Accounts21 = Accounts.builder()
                .id(21L)
                .balance(11230.00f)
                .accountTypes(AccountType21)
                .active(true)
                .approved(true)
                .confirmed(true)
                .debt_interest(0.04f)
                .limit(190000)
                .users(User21)
                .build();


        var Card21 = Cards.builder()
                .id(21L)
                .card_num("4419231168953886")
                .exp_date(new SimpleDateFormat("MM/yyyy").parse(sDate1))
                .accounts(Accounts21)
                .pin(600)
                .cvc2(447)
                .cvc1(996)
                .build();


        accountTypeRepository.save(AccountType21);
        userRepository.save(User21);
        accountsRepository.save(Accounts21);
        cardsRepository.save(Card21);

        var AccountType22 = AccountTypes.builder()
                .id(22L)
                .foodie_pts(0.0f)
                .savings_interest(0.0f)
                .cashBack(0.0f)
                .annual_fee(0.00f)
                .late_fee(0.00f)
                .build();



        var User22 = Users.builder()
                .id(25L)
                .username("Peadddrreer")
                .first_name("Xanthus")
                .last_name("Gentry")
                .email("smoothstack@email.com")
                .is_admin(false)
                .address("Ap #876-8402 Mauris Ave")
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .build();


        var Accounts22 = Accounts.builder()
                .id(22L)
                .balance(11230.00f)
                .accountTypes(AccountType22)
                .active(true)
                .approved(true)
                .confirmed(true)
                .debt_interest(0.05f)
                .limit(190000)
                .users(User22)
                .build();


        var Card22 = Cards.builder()
                .id(22L)
                .card_num("4419231168953886")
                .exp_date(new SimpleDateFormat("MM/yyyy").parse(sDate1))
                .accounts(Accounts22)
                .pin(600)
                .cvc2(447)
                .cvc1(996)
                .build();


        accountTypeRepository.save(AccountType22);
        userRepository.save(User22);
        accountsRepository.save(Accounts22);
        cardsRepository.save(Card22);


    }

    private void loadAllCreditAccounts() throws ParseException {
        var AccountType1 = AccountTypes.builder()
                .id(1L)
                .foodie_pts(0.06f)
                .savings_interest(0.00f)
                .cashBack(0.03f)
                .annual_fee(100)

                .late_fee(29.00f)
                .build();

        var User1 = Users.builder()
                .id(4L)
                .username("dan")
                .first_name("Timothy")
                .last_name("Mcdaniel")
                .email("smoothstack@email.com")
                .is_admin(false)
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .address("Ap #705-3955 Ultricies St.")
                .permissions("")
                .build();

        var Accounts1 = Accounts.builder()
                .id(1L)
                .balance(900.00f)
                .accountTypes(AccountType1)
                .active(true)
                .approved(true)
                .confirmed(true)
                .due_date(new Date())
                .debt_interest(0.06f)
                .limit(10000)
                .payment_due(60)
                .due_date(new Date())
                .users(User1)
                .build();

        var Card1 = Cards.builder()
                .id(1L)
                .card_num("231923393855048")
                .accounts(Accounts1)
                .cvc1(123)
                .cvc2(456)
                .pin(567)
                .exp_date(simpleDateFormat.parse(expDate))
                .build();
        accountTypeRepository.save(AccountType1);
        userRepository.save(User1);
        accountsRepository.save(Accounts1);
        cardsRepository.save(Card1);


        var AccountType2 = AccountTypes.builder()
                .id(2L)
                .foodie_pts(0.00f)
                .savings_interest(0.00f)
                .cashBack(0.03f)
                .annual_fee(100)

                .late_fee(29.00f)
                .build();

        var User2 = Users.builder()
                .id(5L)
                .username("intellij")
                .first_name("Minerva")
                .last_name("Calhoun")
                .email("smoothstack@email.com")
                .is_admin(false)
                .address("8948 Turpis. Rd.")
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .build();

        var Accounts2 = Accounts.builder()
                .id(2L)
                .balance(1230.00f)
                .accountTypes(AccountType2)
                .active(true)
                .approved(true)
                .confirmed(true)
                .due_date(new Date())
                .debt_interest(0.03f)
                .limit(20000)
                .payment_due(120)

                .users(User2)
                .build();


        var Card2 = Cards.builder()
                .id(2L)
                .card_num("231923582648170")
                .accounts(Accounts2)
                .exp_date(new SimpleDateFormat("MM/yyyy").parse(sDate1))
                .pin(123)
                .cvc2(467)
                .cvc1(346)
                .build();
        accountTypeRepository.save(AccountType2);
        userRepository.save(User2);
        accountsRepository.save(Accounts2);
        cardsRepository.save(Card2);

        var AccountType3 = AccountTypes.builder()
                .id(3L)
                .foodie_pts(0.00f)
                .savings_interest(0.00f)
                .cashBack(0.00f)
                .annual_fee(100)
                .late_fee(0.00f)
                .build();


        var User3 = Users.builder()
                .id(6L)
                .username("Xcode")
                .first_name("Barrett")
                .last_name("Manning")
                .email("smoothstack@email.com")
                .is_admin(false)
                .address("954-3873 Et, Rd.")
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .build();


        var Accounts3 = Accounts.builder()
                .id(3L)
                .balance(5230.00f)
                .accountTypes(AccountType3)
                .active(true)
                .approved(true)
                .confirmed(true)
                .due_date(new Date())
                .debt_interest(0.02f)
                .limit(90000)
                .users(User3)
                .payment_due(467)
                .build();


        var Card3 = Cards.builder()
                .id(3L)
                .card_num("2319236312374614")
                .accounts(Accounts3)
                .exp_date(new SimpleDateFormat("MM/yyyy").parse(sDate1))
                .pin(223)
                .cvc2(447)
                .cvc1(346)
                .build();
        accountTypeRepository.save(AccountType3);
        userRepository.save(User3);
        accountsRepository.save(Accounts3);
        cardsRepository.save(Card3);

        var AccountType4 = AccountTypes.builder()
                .id(4L)
                .foodie_pts(0.06f)
                .savings_interest(0.03f)
                .cashBack(0.03f)
                .annual_fee(100)
                .late_fee(29.00f)
                .build();

        var User4 = Users.builder()
                .id(7L)
                .username("eclipse")
                .first_name("Geoffrey")
                .last_name("Vincent")
                .email("smoothstack@email.com")
                .is_admin(false)
                .address("491-6511 Aliquet Rd.")
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .build();



        var Accounts4 = Accounts.builder()
                .id(4L)
                .balance(8230.00f)
                .accountTypes(AccountType4)
                .active(true)
                .approved(true)
                .confirmed(true)
                .due_date(new Date())
                .debt_interest(0.01f)
                .limit(120000)
                .payment_due(675)
                .users(User4)
                .build();



        var Card4 = Cards.builder()
                .id(4L)
                .card_num("231923738762800")
                .exp_date(new SimpleDateFormat("MM/yyyy").parse(sDate1))
                .accounts(Accounts4)
                .pin(213)
                .cvc2(447)
                .cvc1(326)
                .build();

        accountTypeRepository.save(AccountType4);
        userRepository.save(User4);
        accountsRepository.save(Accounts4);
        cardsRepository.save(Card4);



        var AccountType5 = AccountTypes.builder()
                .id(5L)
                .foodie_pts(0.00f)
                .savings_interest(0.03f)
                .cashBack(0.0f)
                .annual_fee(50.00f)
                .late_fee(0.00f)
                .build();



        var User5 = Users.builder()
                .id(8L)
                .username("Xcode")
                .first_name("Dana")
                .last_name("Elliott")
                .email("smoothstack@email.com")
                .is_admin(false)
                .address("Ap #573-3947 Donec Ave")
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .build();


        var Accounts5 = Accounts.builder()
                .id(5L)
                .balance(18230.00f)
                .accountTypes(AccountType4)
                .active(true)
                .approved(true)
                .confirmed(true)
                .payment_due(786)
                .due_date(new Date())
                .debt_interest(0.06f)
                .limit(120000)
                .users(User5)

                .build();


        var Card5 = Cards.builder()
                .id(5L)
                .card_num("2319231168953886")
                .exp_date(new SimpleDateFormat("MM/yyyy").parse(sDate1))
                .accounts(Accounts5)
                .pin(200)
                .cvc2(447)
                .cvc1(326)
                .build();


        accountTypeRepository.save(AccountType5);
        userRepository.save(User5);
        accountsRepository.save(Accounts5);
        cardsRepository.save(Card5);



        var AccountType6 = AccountTypes.builder()
                .id(6L)
                .foodie_pts(0.03f)
                .savings_interest(0.00f)
                .cashBack(0.01f)
                .annual_fee(60)
                .late_fee(29.00f)
                .build();

        var User6 = Users.builder()
                .id(9L)
                .username("cuppies")
                .first_name("Kelly")
                .last_name("Le")
                .email("smoothstack@email.com")
                .is_admin(false)
                .address("Ap #627-639 Non, Street")
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .build();

        var Accounts6 = Accounts.builder()
                .id(6L)
                .balance(9004f)
                .accountTypes(AccountType6)
                .active(true)
                .approved(true)
                .confirmed(true)
                .due_date(new Date())
                .debt_interest(0.06f)
                .limit(10000)
                .payment_due(111)
                .users(User6)
                .build();

        var Card6 = Cards.builder()
                .id(6L)
                .card_num("231923393855048")
                .accounts(Accounts6)
                .exp_date(simpleDateFormat.parse(expDate))
                .cvc1(103)
                .cvc2(456)
                .pin(547)
                .exp_date(new Date())
                .build();
        accountTypeRepository.save(AccountType6);
        userRepository.save(User6);
        accountsRepository.save(Accounts6);
        cardsRepository.save(Card6);


        var AccountType7 = AccountTypes.builder()
                .id(7L)
                .foodie_pts(0.04f)
                .savings_interest(0.00f)
                .cashBack(0.02f)
                .annual_fee(0)
                .late_fee(29.00f)
                .build();

        var User7 = Users.builder()
                .id(10L)
                .username("microsoft")
                .first_name("Tamekah")
                .last_name("Bond")
                .email("smoothstack@email.com")
                .is_admin(false)
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .address("P.O. Box 749, 1638 Vel, Rd.")
                .permissions("")
                .build();

        var Accounts7 = Accounts.builder()
                .id(7L)
                .balance(2300.00f)
                .accountTypes(AccountType7)
                .active(true)
                .approved(true)
                .confirmed(true)
                .due_date(new Date())
                .debt_interest(0.07f)
                .limit(50000)
                .payment_due(222)
                .users(User2)
                .build();


        var Card7 = Cards.builder()
                .id(7L)
                .card_num("2319231126481670")
                .exp_date(new SimpleDateFormat("MM/yyyy").parse(sDate1))
                .accounts(Accounts7)
                .pin(123)
                .cvc2(467)
                .cvc1(346)
                .build();
        accountTypeRepository.save(AccountType7);
        userRepository.save(User7);
        accountsRepository.save(Accounts7);
        cardsRepository.save(Card7);

        var AccountType8 = AccountTypes.builder()
                .id(8L)
                .foodie_pts(0.02f)
                .savings_interest(0.00f)
                .cashBack(0.015f)
                .annual_fee(0)
                .late_fee(30.00f)
                .build();


        var User8 = Users.builder()
                .id(11L)
                .username("Xcode")
                .first_name("Cadman")
                .last_name("Dillon")
                .email("smoothstack@email.com")
                .is_admin(false)
                .address("Ap #157-9547 Diam St.")
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .build();


        var Accounts8 = Accounts.builder()
                .id(8L)
                .balance(4230.00f)
                .accountTypes(AccountType8)
                .active(true)
                .approved(true)
                .confirmed(true)
                .due_date(new Date())
                .debt_interest(0.06f)
                .limit(70000)
                .users(User8)
                .payment_due(356)
                .build();


        var Card8 = Cards.builder()
                .id(8L)
                .card_num("4519236312374614")
                .exp_date(new SimpleDateFormat("MM/yyyy").parse(sDate1))
                .accounts(Accounts8)
                .pin(903)
                .cvc2(127)
                .cvc1(306)
                .build();
        accountTypeRepository.save(AccountType8);
        userRepository.save(User8);
        accountsRepository.save(Accounts8);
        cardsRepository.save(Card8);

        var AccountType9 = AccountTypes.builder()
                .id(9L)
                .foodie_pts(0.07f)
                .savings_interest(0.0f)
                .cashBack(0.01f)
                .annual_fee(50)
                .late_fee(29.00f)
                .build();

        var User9 = Users.builder()
                .id(12L)
                .username("foldphone")
                .first_name("Phyllis")
                .last_name("Langley")
                .email("smoothstack@email.com")
                .is_admin(false)
                .address("889-7883 Ut Road")
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .build();



        var Accounts9 = Accounts.builder()
                .id(9L)
                .balance(2030.00f)
                .accountTypes(AccountType9)
                .active(true)
                .approved(true)
                .confirmed(true)
                .due_date(new Date())
                .debt_interest(0.06f)
                .limit(180000)
                .payment_due(135)
                .users(User9)
                .build();



        var Card9 = Cards.builder()
                .id(9L)
                .card_num("666923738762800")
                .exp_date(new SimpleDateFormat("MM/yyyy").parse(sDate1))
                .accounts(Accounts9)
                .pin(883)
                .cvc2(777)
                .cvc1(326)
                .build();

        accountTypeRepository.save(AccountType9);
        userRepository.save(User9);
        accountsRepository.save(Accounts9);
        cardsRepository.save(Card9);



        var AccountType10 = AccountTypes.builder()
                .id(10L)
                .foodie_pts(0.06f)
                .savings_interest(0.03f)
                .cashBack(0.0f)
                .annual_fee(70.00f)
                .late_fee(10.00f)
                .build();



        var User10 = Users.builder()
                .id(13L)
                .username("BananaY")
                .first_name("Clinton")
                .last_name("Steele")
                .email("smoothstack@email.com")
                .is_admin(false)
                .address("Ap #901-4021 Commodo Av.")
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .build();


        var Accounts10 = Accounts.builder()
                .id(10L)
                .balance(11230.00f)
                .accountTypes(AccountType10)
                .active(true)
                .approved(true)
                .confirmed(true)
                .payment_due(214)
                .due_date(new Date())
                .debt_interest(0.08f)
                .limit(190000)
                .users(User10)
                .build();


        var Card10 = Cards.builder()
                .id(10L)
                .card_num("2319231168953886")
                .exp_date(new SimpleDateFormat("MM/yyyy").parse(sDate1))
                .accounts(Accounts10)
                .pin(220)
                .cvc2(447)
                .cvc1(996)
                .build();


        accountTypeRepository.save(AccountType10);
        userRepository.save(User10);
        accountsRepository.save(Accounts10);
        cardsRepository.save(Card10);



        var AccountType11 = AccountTypes.builder()
                .id(11L)
                .foodie_pts(0.06f)
                .savings_interest(0.03f)
                .cashBack(0.0f)
                .annual_fee(70.00f)
                .savings_interest(0.0f)
                .late_fee(10.00f)
                .build();



        var User11 = Users.builder()
                .id(14L)
                .username("BdfdsY")
                .first_name("Ori")
                .last_name("Roberson")
                .email("smoothstack@email.com")
                .is_admin(false)
                .address("Ap #901-4021 Commodo Av.")
                .password(passwordEncoder.encode("dan123"))
                .roles("USER")
                .permissions("")
                .build();


        var Accounts11 = Accounts.builder()
                .id(11L)
                .balance(78230.00f)
                .accountTypes(AccountType11)
                .active(true)
                .approved(true)
                .confirmed(true)
                .payment_due(356)
                .due_date(new Date())
                .debt_interest(0.09f)
                .limit(190000)
                .users(User11)
                .build();


        var Card11 = Cards.builder()
                .id(11L)
                .card_num("2355531168953886")
                .exp_date(new SimpleDateFormat("MM/yyyy").parse(sDate1))
                .accounts(Accounts11)
                .pin(220)
                .cvc2(897)
                .cvc1(996)
                .build();


        accountTypeRepository.save(AccountType11);
        userRepository.save(User11);
        accountsRepository.save(Accounts11);
        cardsRepository.save(Card11);




    }

}
