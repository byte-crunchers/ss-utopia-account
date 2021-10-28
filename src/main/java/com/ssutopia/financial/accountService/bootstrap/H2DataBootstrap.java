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
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;

import java.time.LocalDate;

import java.util.Date;

@Profile("!test")
@Component
@RequiredArgsConstructor
public class H2DataBootstrap implements CommandLineRunner {
    private final AccountTypeRepository accountTypeRepository;
    private final AccountsRepository accountsRepository;
    private final CardsRepository cardsRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    String sDate1="12/2025";
    String expDate = "11/25"; // for example
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");
    Date dueDate;


    @Override
    public void run(String... args) throws Exception {
        if(accountTypeRepository.count()==0){

        	Calendar c = Calendar.getInstance();
        	c.setTime(new Date()); 
        	c.add(Calendar.DATE, 30);
        	dueDate = c.getTime();
        	
            loadUser();
            loadAllCreditAccounts();
            loadAllDebitAccounts();
        }
    }

    private void loadUser(){

        var User1 = Users.builder()
                .id(1L)
                .username("dan")
                .first_name("Dan")
                .last_name("Wo")
                .address( "529-5103 Hendrerit. Rd.")
                .email("smoothstack@email.com")
                .city("Miami Beach")
                .state("FL")
                .zip(33139)
                .phone(7778889999L)
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
    	
    	//only 1 type of debit card
    	
        var AccountType0 = AccountTypes.builder()
                .id("Utopia Debit")
                .foodie_pts(0.0f)
                .savings_interest(0.0f)
                .cashBack(0.0f)
                .annual_fee(0)
                .late_fee(0.0f)
                .build();
        accountTypeRepository.save(AccountType0);

        //checking account instances:

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
                .accountTypes(AccountType0)
                .active(true)
                .approved(true)
                .confirmed(true)
                .debt_interest(0.04f)
                .limit(10000)

                .users(User12)
                .build();

        var Card12 = Cards.builder()
                .card_num(999923393855048L)
                .exp_date(LocalDate.now())
                .accounts(Accounts12)
                .cvc1(823)
                .cvc2(756)
                .pin(567)
                .build();
        userRepository.save(User12);
        accountsRepository.save(Accounts12);
        cardsRepository.save(Card12);


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
                .accountTypes(AccountType0)
                .active(true)
                .approved(true)
                .confirmed(true)
                .debt_interest(0.03f)
                .limit(20000)

                .users(User13)
                .build();


        var Card13 = Cards.builder()
                .card_num(9989235826481720L)
                .exp_date(LocalDate.now())
                .accounts(Accounts13)
                .pin(123)
                .cvc2(987)
                .cvc1(346)
                .build();
        userRepository.save(User13);
        accountsRepository.save(Accounts13);
        cardsRepository.save(Card13);


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
                .accountTypes(AccountType0)
                .active(true)
                .approved(true)
                .confirmed(true)
                .debt_interest(0.02f)
                .limit(90000)
                .users(User14)

                .build();


        var Card14 = Cards.builder()
                .card_num(9999232631274614L)
                .exp_date(LocalDate.now())
                .accounts(Accounts14)
                .pin(963)
                .cvc2(447)
                .cvc1(346)
                .build();
        userRepository.save(User14);
        accountsRepository.save(Accounts14);
        cardsRepository.save(Card14);


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
                .accountTypes(AccountType0)
                .active(true)
                .approved(true)
                .confirmed(true)
                .debt_interest(0.0f)
                .limit(190000)

                .users(User15)
                .build();



        var Card15 = Cards.builder()
                .card_num(8889237328762800L)
                .exp_date(LocalDate.now())
                .accounts(Accounts15)
                .pin(213)
                .cvc2(447)
                .cvc1(326)
                .build();

        userRepository.save(User15);
        accountsRepository.save(Accounts15);
        cardsRepository.save(Card15);



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
                .accountTypes(AccountType0)
                .active(true)
                .approved(true)
                .confirmed(true)
                .debt_interest(0.0f)
                .limit(120000)
                .users(User16)

                .build();


        var Card16 = Cards.builder()
                .card_num(52566231168953886L)
                .exp_date(LocalDate.now())
                .accounts(Accounts16)
                .pin(200)
                .cvc2(447)
                .cvc1(326)
                .build();


        userRepository.save(User16);
        accountsRepository.save(Accounts16);
        cardsRepository.save(Card16);


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
                .accountTypes(AccountType0)
                .active(true)
                .approved(true)
                .confirmed(true)
                .debt_interest(0.0f)
                .limit(20000)

                .users(User17)
                .build();

        var Card17 = Cards.builder()
                .card_num(4412923393855048L)
                .exp_date(LocalDate.now())
                .accounts(Accounts17)
                .cvc1(603)
                .cvc2(456)
                .pin(547)
                .exp_date(LocalDate.now())
                .build();
        userRepository.save(User17);
        accountsRepository.save(Accounts17);
        cardsRepository.save(Card17);


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
                .accountTypes(AccountType0)
                .active(true)
                .approved(true)
                .confirmed(true)
                .debt_interest(0.03f)
                .limit(50000)

                .users(User18)
                .build();


        var Card18 = Cards.builder()
                .card_num(2321923112648170L)
                .exp_date(LocalDate.now())
                .accounts(Accounts18)
                .pin(123)
                .cvc2(467)
                .cvc1(346)
                .build();
        userRepository.save(User18);
        accountsRepository.save(Accounts18);
        cardsRepository.save(Card18);


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
                .accountTypes(AccountType0)
                .active(true)
                .approved(true)
                .confirmed(true)
                .debt_interest(0.02f)
                .limit(70000)
                .users(User19)

                .build();


        var Card19 = Cards.builder()
                .card_num(7719232631274614L)
                .exp_date(LocalDate.now())
                .accounts(Accounts19)
                .pin(903)
                .cvc2(127)
                .cvc1(306)
                .build();
        userRepository.save(User19);
        accountsRepository.save(Accounts19);
        cardsRepository.save(Card19);

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
                .accountTypes(AccountType0)
                .active(true)
                .approved(true)
                .confirmed(true)
                .debt_interest(0.01f)
                .limit(180000)

                .users(User20)
                .build();



        var Card20 = Cards.builder()
                .card_num(6569237387262800L)
                .exp_date(LocalDate.now())
                .accounts(Accounts20)
                .pin(883)
                .cvc2(777)
                .cvc1(326)
                .build();

        userRepository.save(User20);
        accountsRepository.save(Accounts20);
        cardsRepository.save(Card20);



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
                .accountTypes(AccountType0)
                .active(true)
                .approved(true)
                .confirmed(true)
                .debt_interest(0.04f)
                .limit(190000)
                .users(User21)
                .build();


        var Card21 = Cards.builder()
                .card_num(44192311268953886L)
                .exp_date(LocalDate.now())
                .accounts(Accounts21)
                .pin(600)
                .cvc2(447)
                .cvc1(996)
                .build();


        userRepository.save(User21);
        accountsRepository.save(Accounts21);
        cardsRepository.save(Card21);

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



        // add 1 test debit card for user dan
//        Users dan = userRepository.findByUsername("dan");
        
        var Accounts22 = Accounts.builder()
                .id(22L)
                .balance(60000.00f)
                .accountTypes(AccountType0)
                .active(true)
                .approved(true)
                .confirmed(true)
                .debt_interest(0.0f)
                .limit(0)
                .users(User22)
                .build();


        var Card22 = Cards.builder()
                .card_num(44192311689532886L)
                .exp_date(LocalDate.now())
                .accounts(Accounts22)
                .pin(600)
                .cvc2(447)
                .cvc1(996)
                .build();


        userRepository.save(User22);
        accountsRepository.save(Accounts22);
        cardsRepository.save(Card22);


    }

    private void loadAllCreditAccounts() throws ParseException {

    	//4 types of credit card accounts
    	
        var AccountType1 = AccountTypes.builder()
                .id("Basic Credit")
                .foodie_pts(0.0f)
                .savings_interest(0.00f)
                .cashBack(0.01f)
                .annual_fee(0)
                .late_fee(29.00f)
                .build();

        var AccountType2 = AccountTypes.builder()
                .id("Platinum Credit")
                .foodie_pts(0.00f)
                .savings_interest(0.00f)
                .cashBack(0.06f)
                .annual_fee(200)
                .late_fee(29.00f)
                .build();

        var AccountType3 = AccountTypes.builder()
                .id("Plus Credit")
                .foodie_pts(0.00f)
                .savings_interest(0.00f)
                .cashBack(0.03f)
                .annual_fee(50)
                .late_fee(29.00f)
                .build();

        var AccountType4 = AccountTypes.builder()
                .id("Foodies Credit")
                .foodie_pts(0.06f)
                .savings_interest(0.0f)
                .cashBack(0.02f)
                .annual_fee(0)
                .late_fee(29.00f)
                .build();

        accountTypeRepository.save(AccountType1);
        accountTypeRepository.save(AccountType2);
        accountTypeRepository.save(AccountType3);
        accountTypeRepository.save(AccountType4);


        //credit account instances:

        var User1 = Users.builder()
                .id(4L)
                .username("dan2")
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
                .card_num(2319233938552048L)
                .accounts(Accounts1)
                .cvc1(123)
                .cvc2(456)
                .pin(567)
                .exp_date(LocalDate.now())
                .build();
        
        userRepository.save(User1);
        accountsRepository.save(Accounts1);
        cardsRepository.save(Card1);



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
                .card_num(2319235282648170L)
                .accounts(Accounts2)
                .exp_date(LocalDate.now())
                .pin(123)
                .cvc2(467)
                .cvc1(346)
                .build();

        userRepository.save(User2);
        accountsRepository.save(Accounts2);
        cardsRepository.save(Card2);


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
                .card_num(1239236312372461L)
                .accounts(Accounts3)
                .exp_date(LocalDate.now())
                .pin(223)
                .cvc2(447)
                .cvc1(346)
                .build();
        userRepository.save(User3);
        accountsRepository.save(Accounts3);
        cardsRepository.save(Card3);

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
                .card_num(2312923738762800L)
                .exp_date(LocalDate.now())
                .accounts(Accounts4)
                .pin(213)
                .cvc2(447)
                .cvc1(326)
                .build();

        userRepository.save(User4);
        accountsRepository.save(Accounts4);
        cardsRepository.save(Card4);




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
                .accountTypes(AccountType1)
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
                .card_num(2319231162895386L)
                .exp_date(LocalDate.now())
                .accounts(Accounts5)
                .pin(200)
                .cvc2(447)
                .cvc1(326)
                .build();


        userRepository.save(User5);
        accountsRepository.save(Accounts5);
        cardsRepository.save(Card5);



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
                .accountTypes(AccountType2)
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
                .card_num(2319233938255048L)
                .accounts(Accounts6)
                .exp_date(LocalDate.now())
                .cvc1(103)
                .cvc2(456)
                .pin(547)

                .build();
        userRepository.save(User6);
        accountsRepository.save(Accounts6);
        cardsRepository.save(Card6);


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
                .accountTypes(AccountType3)
                .active(true)
                .approved(true)
                .confirmed(true)
                .due_date(new Date())
                .debt_interest(0.07f)
                .limit(50000)
                .payment_due(222)
                .users(User7)
                .build();


        var Card7 = Cards.builder()
                .card_num(3192311264281670L)
                .exp_date(LocalDate.now())
                .accounts(Accounts7)
                .pin(123)
                .cvc2(467)
                .cvc1(346)
                .build();
        userRepository.save(User7);
        accountsRepository.save(Accounts7);
        cardsRepository.save(Card7);


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
                .accountTypes(AccountType4)
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
                .card_num(5192363123746214L)
                .exp_date(LocalDate.now())
                .accounts(Accounts8)
                .pin(903)
                .cvc2(127)
                .cvc1(306)
                .build();
        userRepository.save(User8);
        accountsRepository.save(Accounts8);
        cardsRepository.save(Card8);


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


//        // add 3 test credit cards for user dan
//        Users dan = userRepository.findByUsername("dan");
        
        var Accounts9 = Accounts.builder()
                .id(9L)
                .balance(2030.00f)
                .accountTypes(AccountType1)
                .active(true)
                .approved(true)
                .confirmed(true)
                .due_date(dueDate)
                .debt_interest(0.015f)
                .limit(5000)
                .payment_due(135)
                .users(User9)
                .build();



        var Card9 = Cards.builder()
                .card_num(6669223738762800L)
                .exp_date(LocalDate.now())
                .accounts(Accounts9)
                .pin(883)
                .cvc2(777)
                .cvc1(326)
                .build();

        userRepository.save(User9);
        accountsRepository.save(Accounts9);
        cardsRepository.save(Card9);




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
                .balance(1230.00f)
                .accountTypes(AccountType2)
                .active(false)
                .approved(true)
                .confirmed(true)
                .payment_due(214)
                .due_date(dueDate)
                .debt_interest(0.018f)
                .limit(9000)
                .users(User10)
                .build();


        var Card10 = Cards.builder()
                .card_num(3219231168953886L)
                .exp_date(LocalDate.now())
                .accounts(Accounts10)
                .pin(220)
                .cvc2(447)
                .cvc1(996)
                .build();


        userRepository.save(User10);
        accountsRepository.save(Accounts10);
        cardsRepository.save(Card10);




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
                .balance(7230.00f)
                .accountTypes(AccountType3)
                .active(true)
                .approved(true)
                .confirmed(true)
                .payment_due(0)
                .due_date(dueDate)
                .debt_interest(0.017f)
                .limit(19000)
                .users(User11)
                .build();


        var Card11 = Cards.builder()
                .card_num(3255531168953886L)
                .exp_date(LocalDate.now())
                .accounts(Accounts11)
                .pin(220)
                .cvc2(897)
                .cvc1(996)
                .build();


        userRepository.save(User11);
        accountsRepository.save(Accounts11);
        cardsRepository.save(Card11);


    }

}
