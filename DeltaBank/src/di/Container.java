package di;

import accounts.serialization.BankAccountSerializationService;
import accounts.services.AccountService;
import factories.CreditCardFactory;
import factories.OwnerFactory;
import transfers.AccountTransferService;
import transfers.TransferLoggerService;
import transfers.TransferService;

public class Container {

    private final TransferLoggerService transferLoggerService;
    private final TransferService transferService;
    private final AccountTransferService accountTransferService;
    private final AccountService accountService;
    private final OwnerFactory ownerFactory;
    private final CreditCardFactory creditCardFactory;
    private final BankAccountSerializationService gsonSerializationService;
    private final BankAccountSerializationService jacksonXmlSerializationService;

    public Container(TransferLoggerService transferLoggerService,
                     TransferService transferService,
                     AccountTransferService accountTransferService,
                     AccountService accountService,
                     OwnerFactory ownerFactory,
                     CreditCardFactory creditCardFactory,
                     BankAccountSerializationService gsonSerializationService,
                     BankAccountSerializationService jacksonXmlSerializationService) {
        this.transferLoggerService = transferLoggerService;
        this.transferService = transferService;
        this.accountTransferService = accountTransferService;
        this.accountService = accountService;
        this.ownerFactory = ownerFactory;
        this.creditCardFactory = creditCardFactory;
        this.gsonSerializationService = gsonSerializationService;
        this.jacksonXmlSerializationService = jacksonXmlSerializationService;
    }

    public TransferLoggerService getTransferLoggerService() {
        return transferLoggerService;
    }

    public TransferService getTransferService() {
        return transferService;
    }

    public AccountTransferService getAccountTransferService() {
        return accountTransferService;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public OwnerFactory getOwnerFactory() {
        return ownerFactory;
    }

    public CreditCardFactory getCreditCardFactory() {
        return creditCardFactory;
    }

    public BankAccountSerializationService getGsonSerializationService() {
        return gsonSerializationService;
    }

    public BankAccountSerializationService getJacksonXmlSerializationService() {
        return jacksonXmlSerializationService;
    }
}
