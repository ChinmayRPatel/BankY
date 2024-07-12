package com.banky.BankY.controller;
import com.banky.BankY.model.Account;
import com.banky.BankY.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public String getAllAccounts(Model model) {
        List<Account> accounts = accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);
        return "index";
    }

    @GetMapping("/create")
    public String createAccountForm(Model model) {
        model.addAttribute("account", new Account());
        return "create";
    }

    @PostMapping("/create")
    public String createAccount(@ModelAttribute Account account) {
        accountService.createAccount(account);
        return "redirect:/accounts";
    }

    @GetMapping("/deposit")
    public String depositForm() {
        return "deposit";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam String accountNumber, @RequestParam Double amount) {
        accountService.deposit(accountNumber, amount);
        return "redirect:/accounts";
    }

    @GetMapping("/withdraw")
    public String withdrawForm() {
        return "withdraw";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam String accountNumber, @RequestParam Double amount) {
        accountService.withdraw(accountNumber, amount);
        return "redirect:/accounts";
    }

    @GetMapping("/transfer")
    public String transferForm() {
        return "transfer";
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam String fromAccountNumber, @RequestParam String toAccountNumber, @RequestParam Double amount) {
        accountService.transfer(fromAccountNumber, toAccountNumber, amount);
        return "redirect:/accounts";
    }

    @GetMapping("/createnewacc")
    public String showCreateAccountForm(Model model) {
        model.addAttribute("account", new Account());
        return "createnewacc";
    }

    @PostMapping("/createnewacc")
    public String createNewAccount(Account account) {
        accountService.createAccount(account.getAccountNumber(), account.getAccountHolderName(), account.getBalance());
        return "redirect:/accounts";
    }

}

