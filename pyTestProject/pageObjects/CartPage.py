from selenium.webdriver.common.by import By


class CartPage:

    def __init__(self, driver):
        self.driver = driver

    cartLinkBtn = (By.XPATH, '//a[contains(text(),"Cart")]')
    deleteProductBtn = (By.XPATH, '//a[contains(text(),"Delete")]')
    placeOrderBtn = (By.XPATH, '//*[contains(text(),"Place Order")]')
    addToCartBtn = (By.XPATH, '//a[contains(text(),"Add to")]')
    purchaseName = (By.ID, "name")
    purchaseCountry = (By.ID, "country")
    purchaseCity = (By.ID, "city")
    purchaseCreditNo = (By.ID, "card")
    purchaseMonth = (By.ID, "month")
    purchaseYear = (By.ID, "year")
    submitPurchase = (By.XPATH, "//button[text()='Purchase']")
    confirmPurchase = (By.XPATH, "//button[text()='OK']")
    
    def getCartLinkBtn(self):
        return self.driver.find_element(*CartPage.cartLinkBtn)

    def getDeleteProductBtn(self):
        return self.driver.find_element(*CartPage.deleteProductBtn)

    def getPlaceOrderBtn(self):
        return self.driver.find_element(*CartPage.placeOrderBtn)

    def getPurchaseName(self):
        return self.driver.find_element(*CartPage.purchaseName)

    def getPurchaseCountry(self):
        return self.driver.find_element(*CartPage.purchaseCountry)

    def getPurchaseCity(self):
        return self.driver.find_element(*CartPage.purchaseCity)

    def getPurchaseCreditNo(self):
        return self.driver.find_element(*CartPage.purchaseCreditNo)

    def getPurchaseMonth(self):
        return self.driver.find_element(*CartPage.purchaseMonth)

    def getPurchaseYear(self):
        return self.driver.find_element(*CartPage.purchaseYear)

    def getSubmitPurchase(self):
        return self.driver.find_element(*CartPage.submitPurchase)

    def getConfirmPurchase(self):
        return self.driver.find_element(*CartPage.confirmPurchase)

    