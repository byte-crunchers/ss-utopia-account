package com.ssutopia.financial.accountService.bootstrap;

import com.ssutopia.financial.accountService.entity.AccountType;
import com.ssutopia.financial.accountService.repository.AccountTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class H2DataBootstrap implements CommandLineRunner {
    private final AccountTypeRepository accountTypeRepository;

    @Override
    public void run(String... args) throws Exception {
        if(accountTypeRepository.count()==0){
          loadAllAccountType();
        }
    }

    private void loadAllAccountType(){
        var AccountType1 = AccountType.builder()
                .id(1L)
                .accountName("checking")
                .apy(0.0f)
                .fee(0)
                .taxes(0.0f)
                .contributionLimits(100000000)
                .withdrawalAgeLimits(14)
                .withdrawalLimits(800)
                .build();
        accountTypeRepository.save(AccountType1);

        var AccountType2 = AccountType.builder()
                .id(2L)
                .accountName("saving")
                .apy(0.005f)
                .fee(0)
                .taxes(0.1f)
                .contributionLimits(100000000)
                .withdrawalAgeLimits(14)
                .withdrawalLimits(6)
                .build();
        accountTypeRepository.save(AccountType2);

        var AccountType3 = AccountType.builder()
                .id(3L)
                .accountName("IRA")
                .apy(0.008f)
                .fee(0)
                .taxes(0.0f)
                .contributionLimits(7000)
                .withdrawalAgeLimits(72)
                .withdrawalLimits(0)
                .build();
        accountTypeRepository.save(AccountType3);

        var AccountType4 = AccountType.builder()
                .id(4L)
                .accountName("investing")
                .apy(0.00f)
                .fee(5)
                .taxes(0.12f)
                .contributionLimits(10000000)
                .withdrawalAgeLimits(14)
                .withdrawalLimits(3)
                .build();
        accountTypeRepository.save(AccountType4);
    }

}
