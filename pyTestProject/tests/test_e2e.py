import allure
import pytest
import time

from allure_commons.types import AttachmentType

from dataFiles.excelHandler import readLoginData, readSignInData, readContactData, readPurchaseData
from pageObjects.CartPage import CartPage
from pageObjects.HomePage import HomePage
from utils.BaseClass import BaseClass


# @pytest.mark.usefixtures("setup")
class TestOne(BaseClass):
    @pytest.mark.parametrize("username, password", readSignInData())
    def test_signup(self, username, password):
        global log
        try:
            log = self.getLogger()
            homePage = HomePage(self.driver)
            homePage.getSignupLink().click()
            log.info("Clicked Signup Page")
            time.sleep(2)
            homePage.getUsernameSignup().clear()
            homePage.getUsernameSignup().send_keys(username)
            homePage.getPasswordSignup().clear()
            homePage.getPasswordSignup().send_keys(password)
            homePage.getSubmitSignup().click()
            time.sleep(2)
            alert = homePage.getAlertSubmitSignup()
            alertMsg = alert.text
            assert alertMsg == "Sign up successful."
            homePage.acceptSubmitSignup()
            time.sleep(2)
            log.info("Signup Complete")
            allure.attach(self.driver.get_screenshot_as_png(), name="testSignup", attachment_type=AttachmentType.PNG)
            # homePage.getCloseSignup().click()
            # time.sleep(2)
        except Exception as e:
            log.info(e)
            print(e)
            allure.attach(self.driver.get_screenshot_as_png(), name="testSignup", attachment_type=AttachmentType.PNG)

    @pytest.mark.parametrize("username, password", readLoginData())
    def test_login(self, username, password):
        global log
        try:
            log = self.getLogger()
            homePage = HomePage(self.driver)
            homePage.getLoginLink().click()
            log.info("Entered Login Page")
            time.sleep(3)
            homePage.getUsernameLogin().send_keys(username)
            homePage.getPasswordLogin().send_keys(password)
            homePage.getSubmitLogin().click()
            log.info("Login Successful")
            time.sleep(2)
            assert homePage.getLoginVerify().text == ("Welcome " +username)
            allure.attach(self.driver.get_screenshot_as_png(), name="testSignup", attachment_type=AttachmentType.PNG)
            # homePage.getLogout().click()
            # log.info("user has logged out")
        except Exception as e:
            log.info(e)
            print(e)
            allure.attach(self.driver.get_screenshot_as_png(), name="testLogin", attachment_type=AttachmentType.PNG)

    def test_add_to_cart(self):
        global log
        try:
            log = self.getLogger()
            homePage = HomePage(self.driver)
            cartPage = CartPage(self.driver)
            homePage.getHomeLinkBtn().click()
            homePage.getPhoneCategoryBtn().click()
            time.sleep(1)
            homePage.getNokiaPhone().click()
            time.sleep(1)
            homePage.getAddToCartBtn().click()
            homePage.acceptAddedToCart()
            log.info("Phone Added to cart")
            homePage.getHomeLinkBtn().click()
            homePage.getLaptopCategoryBtn().click()
            time.sleep(1)
            homePage.getAppleLaptop().click()
            time.sleep(1)
            homePage.getAddToCartBtn().click()
            homePage.acceptAddedToCart()
            log.info("Laptop Added to cart")
            homePage.getHomeLinkBtn().click()
            homePage.getMonitorCategoryBtn().click()
            time.sleep(1)
            homePage.getAppleMonitor().click()
            time.sleep(1)
            homePage.getAddToCartBtn().click()
            homePage.acceptAddedToCart()
            log.info("Monitor Added to cart")
            homePage.getHomeLinkBtn().click()
            time.sleep(1)
            cartPage.getCartLinkBtn().click()
            time.sleep(3)
            log.info("Cart Page is visible")
            cartPage.getDeleteProductBtn().click()
            time.sleep(1)
            log.info("1 product was removed from cart")
            homePage.getHomeLinkBtn().click()
            allure.attach(self.driver.get_screenshot_as_png(), name="testAddToCart", attachment_type=AttachmentType.PNG)
        except Exception as e:
            log.info(e)
            print(e)
            allure.attach(self.driver.get_screenshot_as_png(), name="testAddToCart", attachment_type=AttachmentType.PNG)

    @pytest.mark.parametrize("name, country, city, card, month, year", readPurchaseData())
    def test_place_order(self, name, country, city, card, month, year):
        try:
            log = self.getLogger()
            cartPage = CartPage(self.driver)
            cartPage.getCartLinkBtn().click()
            time.sleep(3)
            cartPage.getPlaceOrderBtn().click()
            time.sleep(1)
            log.info("place order was clicked")
            cartPage.getPurchaseName().send_keys(name)
            cartPage.getPurchaseCountry().send_keys(country)
            cartPage.getPurchaseCity().send_keys(city)
            cartPage.getPurchaseCreditNo().send_keys(card)
            cartPage.getPurchaseMonth().send_keys(month)
            cartPage.getPurchaseYear().send_keys(year)
            cartPage.getSubmitPurchase().click()
            time.sleep(1)
            cartPage.getConfirmPurchase().click()
            log.info("purchase is confirmed")
            allure.attach(self.driver.get_screenshot_as_png(), name="testPurchaseOrder", attachment_type=AttachmentType.PNG)
        except Exception as e:
            log.info(e)
            print(e)
            allure.attach(self.driver.get_screenshot_as_png(), name="testPurchaseOrder", attachment_type=AttachmentType.PNG)

    @pytest.mark.parametrize("contactEmail, contactName, contactMessage", readContactData())
    def test_send_message(self, contactEmail, contactName, contactMessage):
        global log
        try:
            log = self.getLogger()
            homePage = HomePage(self.driver)
            homePage.getHomeLinkBtn().click()
            homePage.getContactLinkBtn().click()
            time.sleep(1)
            log.info("Contact tab was clicked")
            homePage.getContactEmail().send_keys(contactEmail)
            homePage.getContactName().send_keys(contactName)
            homePage.getContactMessage().send_keys(contactMessage)
            homePage.getSendMessage().click()
            time.sleep(1)
            alert = homePage.getAlertacceptContactMessage()
            alertMsg = alert.text
            assert alertMsg == "Thanks for the message!!"
            homePage.acceptContactMessage()
            log.info("Message was sent from contact tab")
            homePage.getHomeLinkBtn().click()
            allure.attach(self.driver.get_screenshot_as_png(), name="testContact", attachment_type=AttachmentType.PNG)
        except Exception as e:
            log.info(e)
            print(e)
            allure.attach(self.driver.get_screenshot_as_png(), name="testContact", attachment_type=AttachmentType.PNG)

    def test_logout(self):
        try:
            log = self.getLogger()
            homePage = HomePage(self.driver)
            homePage.getHomeLinkBtn().click()
            time.sleep(1)
            homePage.getLogout().click()
            log.info("user has logged out")
            allure.attach(self.driver.get_screenshot_as_png(), name="testSignup", attachment_type=AttachmentType.PNG)
        except Exception as e:
            log.info(e)
            print(e)
            allure.attach(self.driver.get_screenshot_as_png(), name="testSignup", attachment_type=AttachmentType.PNG)
