from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


class HomePage:

    def __init__(self, driver):
        self.driver = driver

    signupLinkBtn = (By.ID, "signin2")
    usernameSignup = (By.ID, "sign-username")
    passwordSignup = (By.ID, "sign-password")
    submitSignup = (By.XPATH, "//button[text()='Sign up']")
    closeSignup = (By.XPATH, "(//button[text()='Close'])[2]")
    loginLinkBtn = (By.ID, "login2")
    usernameLogin = (By.ID, "loginusername")
    passwordLogin = (By.ID, "loginpassword")
    loginVerify = (By.ID, "nameofuser")
    submitLogin = (By.XPATH, "//button[text()='Log in']")
    homeLinkBtn = (By.XPATH, '//a[contains(text(),"Home")]')
    phoneCategoryBtn = (By.XPATH, '//a[contains(text(),"Phones")]')
    laptopCategoryBtn = (By.XPATH, '//a[contains(text(),"Laptops")]')
    monitorCategoryBtn = (By.XPATH, '//a[contains(text(),"Monitors")]')
    nokiaPhone = (By.XPATH, '//a[contains(text(),"Nokia")]')
    appleLaptop = (By.XPATH, '//a[contains(text(),"Pro")]')
    appleMonitor = (By.XPATH, '//a[contains(text(),"Apple")]')
    addToCartBtn = (By.XPATH, '//a[contains(text(),"Add to")]')
    contactLinkBtn = (By.XPATH, '//a[contains(text(),"Contact")]')
    contactEmail = (By.ID, "recipient-email")
    contactName = (By.ID, "recipient-name")
    contactMessage = (By.ID, "message-text")
    sendMessage = (By.XPATH, "//button[text()='Send message']")
    logoutBtn = (By.ID, "logout2")

    def getSignupLink(self):
        return self.driver.find_element(*HomePage.signupLinkBtn)

    def getUsernameSignup(self):
        return self.driver.find_element(*HomePage.usernameSignup)

    def getPasswordSignup(self):
        return self.driver.find_element(*HomePage.passwordSignup)

    def getSubmitSignup(self):
        return self.driver.find_element(*HomePage.submitSignup)

    def getCloseSignup(self):
        return self.driver.find_element(*HomePage.closeSignup)

    def acceptSubmitSignup(self):
        WebDriverWait(self.driver, 10).until(EC.alert_is_present())
        return self.driver.switch_to.alert.accept()

    def getLoginLink(self):
        return self.driver.find_element(*HomePage.loginLinkBtn)

    def getUsernameLogin(self):
        return self.driver.find_element(*HomePage.usernameLogin)

    def getPasswordLogin(self):
        return self.driver.find_element(*HomePage.passwordLogin)

    def getSubmitLogin(self):
        return self.driver.find_element(*HomePage.submitLogin)

    def getLoginVerify(self):
        return self.driver.find_element(*HomePage.loginVerify)

    def getHomeLinkBtn(self):
        return self.driver.find_element(*HomePage.homeLinkBtn)

    def getPhoneCategoryBtn(self):
        return self.driver.find_element(*HomePage.phoneCategoryBtn)

    def getLaptopCategoryBtn(self):
        return self.driver.find_element(*HomePage.laptopCategoryBtn)

    def getMonitorCategoryBtn(self):
        return self.driver.find_element(*HomePage.monitorCategoryBtn)

    def getNokiaPhone(self):
        return self.driver.find_element(*HomePage.nokiaPhone)

    def getAppleLaptop(self):
        return self.driver.find_element(*HomePage.appleLaptop)

    def getAppleMonitor(self):
        return self.driver.find_element(*HomePage.appleMonitor)

    def acceptAddedToCart(self):
        WebDriverWait(self.driver, 10).until(EC.alert_is_present())
        return self.driver.switch_to.alert.accept()

    def getAddToCartBtn(self):
        return self.driver.find_element(*HomePage.addToCartBtn)

    def getContactLinkBtn(self):
        return self.driver.find_element(*HomePage.contactLinkBtn)

    def getContactName(self):
        return self.driver.find_element(*HomePage.contactName)

    def getContactEmail(self):
        return self.driver.find_element(*HomePage.contactEmail)

    def getContactMessage(self):
        return self.driver.find_element(*HomePage.contactMessage)

    def getSendMessage(self):
        return self.driver.find_element(*HomePage.sendMessage)

    def acceptContactMessage(self):
        WebDriverWait(self.driver, 10).until(EC.alert_is_present())
        return self.driver.switch_to.alert.accept()

    def getLogout(self):
        return self.driver.find_element(*HomePage.logoutBtn)

    def getAlertSubmitSignup(self):
        WebDriverWait(self.driver, 10).until(EC.alert_is_present())
        return self.driver.switch_to.alert

    def getAlertacceptContactMessage(self):
        WebDriverWait(self.driver, 10).until(EC.alert_is_present())
        return self.driver.switch_to.alert
