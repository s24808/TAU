import asyncio


class InsufficientFundsError(Exception):
    pass


class Account:
    def __init__(self, account_number: str, owner: str, balance: float = 0.0):
        self.account_number = account_number
        self.owner = owner
        self.balance = balance

    def deposit(self, amount: float):
        """"Dodawanie środków do konta"""
        if amount <= 0:
            raise ValueError("Kwota wpłaty musi być większa niż 0")
        self.balance += amount

    def withdraw(self, amount: float):
        """Wypłacanie środków z konta"""
        if amount <= 0:
            raise ValueError("Musisz mieć więcej niż 0 na koncie")
        if self.balance < amount:
            raise InsufficientFundsError("Niewystarczające środki na koncie")
        self.balance -= amount

    async def transfer(self, to_account: "Account", amount: float):
        """Transfer środów na inne konto"""
        if amount <= 0:
            raise ValueError("Kwota przelewu musi być większa niż 0")
        await asyncio.sleep(1)
        self.withdraw(amount)
        to_account.deposit(amount)


class Bank:
    def __init__(self):
        self.accounts = {}

    def create_account(self, account_number: str, owner: str, initial_balance: float):
        """Tworzenie konta"""
        if account_number in self.accounts:
            raise ValueError("Numer konta już istnieje")
        if initial_balance < 0:
            raise ValueError("Saldo musi być większe od 0")

        self.accounts[account_number] = Account(account_number, owner, initial_balance)

    def get_account(self, account_number: str):
        """Zwracanmie konto na podstawie numeru"""
        if account_number not in self.accounts:
            raise ValueError("Zły numer konta")
        return self.accounts[account_number]

    async def process_transaction(self, transaction_func):
        """Transkacja"""
        await asyncio.sleep(1)
        return await transaction_func()
