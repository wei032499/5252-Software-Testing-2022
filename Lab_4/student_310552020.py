from selenium import webdriver
from selenium.webdriver.firefox.service import Service
from webdriver_manager.firefox import GeckoDriverManager

from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.keys import Keys

driver = webdriver.Firefox(service=Service(GeckoDriverManager().install()))

driver.maximize_window()
driver.get("https://www.nycu.edu.tw/")


# NYCU
# 最新消息
driver.find_element(By.CSS_SELECTOR,"a[href='https://www.nycu.edu.tw/news-network/']").click()

# 第一筆公告
driver.find_element(By.CSS_SELECTOR,"ul.su-posts>li>a").click()

print("Title:",driver.find_element(By.CSS_SELECTOR,"article>header").text)
print("Content:",driver.find_element(By.CSS_SELECTOR,"article>.entry-content").text)



# Google search
driver.switch_to.new_window('tab')
driver.get("https://www.google.com")
driver.execute_script('document.querySelector("form[action=\\"/search\\"] input").value = "310552020"')
driver.execute_script('document.querySelector("form[action=\\"/search\\"]").submit()')
WebDriverWait(driver, 10).until(lambda d: d.find_element(By.CSS_SELECTOR,"h3"))
print("===============================")
print("Google search result:",driver.find_elements(By.CSS_SELECTOR,"h3")[1].text)

# close browser
driver.quit()