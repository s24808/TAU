import pytest
from unittest.mock import AsyncMock
from bank_system import Account, Bank, InsufficientFundsError


@pytest.fixture
def account():
    return Account(account_number="123456", owner="Filip Labuda", balance=100.0)


@pytest.fixture
def bank():
    return Bank()


###Testy dla klasy Account###

def test_deposit(account):
    """Sprawdzenie wpłat"""
    account.deposit(50)
    assert account.balance == 150.0


def test_deposit_negative(account):
    """Sprawdzenie niepoprawnej wpłaty"""
    with pytest.raises(ValueError, match="Kwota wpłaty musi być większa niż 0"):
        account.deposit(-10)


def test_withdraw(account):
    """Sprawdzenie wypłat"""
    account.withdraw(30)
    assert account.balance == 70.0


def test_withdraw_insufficient_funds(account):
    """Sprawdzenie wypłaty większej od salda"""
    with pytest.raises(InsufficientFundsError, match="Niewystarczające środki na koncie"):
        account.withdraw(200)


@pytest.mark.asyncio
async def test_transfer(account):
    """Sprawdzenie transferu między kontami, asynchronicznie"""
    target_account = Account(account_number="654321", owner="Jan Jakistam", balance=50.0)
    await account.transfer(target_account, 50)
    assert account.balance == 50.0
    assert target_account.balance == 100.0


@pytest.mark.asyncio
async def test_transfer_insufficient_funds(account):
    """Sprawdzenie transferu, gdy na koncie jest za mało środków."""
    target_account = Account(account_number="654321", owner="Jan Jakistam", balance=50.0)
    with pytest.raises(InsufficientFundsError, match="Niewystarczające środki na koncie"):
        await account.transfer(target_account, 200)


###Testy dla klasy Bank###

def test_create_account(bank):
    """Sprawdzenie tworzenia konta."""
    bank.create_account("987654", "Filip Labuda", 500)
    assert "987654" in bank.accounts
    assert bank.accounts["987654"].balance == 500


def test_create_account_duplicate(bank):
    """Sprawdzenie, czy konto jest unikalne"""
    bank.create_account("111111", "Maria Kowalska", 200)
    with pytest.raises(ValueError, match="Numer konta już istnieje"):
        bank.create_account("111111", "Krzysztof Kowalski", 300)


def test_get_account(bank):
    """Sprawdzenie pobierania konta"""
    bank.create_account("333333", "Marta Nowak", 100)
    account = bank.get_account("333333")
    assert account.owner == "Marta Nowak"
    assert account.balance == 100


@pytest.mark.asyncio
async def test_process_transaction(bank):
    """Sprawdzenie procesowania transakcji, asynchronicznie"""
    async def dummy_transaction():
        return "Transakcja zakończona"
    result = await bank.process_transaction(dummy_transaction)
    assert result == "Transakcja zakończona"


###Mockowanie###
@pytest.mark.asyncio
async def test_mock_external_authorization():
    """Sprawdzenie mockowania"""
    mock_auth = AsyncMock(return_value=True)
    async def fake_transaction():
        return await mock_auth()
    result = await fake_transaction()
    mock_auth.assert_called_once()
    assert result is True
