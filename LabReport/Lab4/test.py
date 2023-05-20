from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
import time

# service = Service(r'./chromedriver.exe')
# chrome = webdriver.Chrome(service=service)

def test_CreateUser():
    service = Service(r'./chromedriver.exe')
    chrome = webdriver.Chrome(service=service)
    chrome.get("http://127.0.0.1:3000/keystone/signin")
    #輸入登入表單資訊
    chrome.find_element(by=By.NAME, value="email").send_keys('demo@keystonejs.com')
    chrome.find_element(by=By.NAME, value="password").send_keys('demo')
    chrome.find_element(by=By.CLASS_NAME, value="css-2960tt").click()
    time.sleep(1)

    chrome.find_element(by=By.LINK_TEXT, value="Users").click()
    time.sleep(1)
    chrome.find_element(by=By.CLASS_NAME, value="css-we21er").click()
    chrome.find_element(by=By.NAME, value="name.first").send_keys("t")
    chrome.find_element(by=By.NAME, value="name.last").send_keys("bj")
    chrome.find_element(by=By.NAME, value="email").send_keys("jjjjj@keystonejs.com")
    chrome.find_element(by=By.NAME, value="password").send_keys("@123456789")
    chrome.find_element(by=By.NAME, value="password_confirm").send_keys("@123456789")
    chrome.find_element(by=By.CLASS_NAME, value="css-h629qq").click()
    #重新導向用戶頁面
    chrome.get('http://127.0.0.1:3000/keystone/users')
    user_list = chrome.find_element(by=By.CLASS_NAME, value="Table").text
    print(user_list)
    assert 'jjjjj@keystonejs.com' in user_list
    chrome.close()

def test_CreatePost():
    service = Service(r'./chromedriver.exe')
    chrome = webdriver.Chrome(service=service)
    chrome.get("http://127.0.0.1:3000/keystone/signin")
    chrome.find_element(by=By.NAME, value="email").send_keys('demo@keystonejs.com')
    chrome.find_element(by=By.NAME, value="password").send_keys('demo')
    chrome.find_element(by=By.CLASS_NAME, value="css-2960tt").click()
    time.sleep(1)

    chrome.find_element(by=By.LINK_TEXT, value="Posts").click()
    time.sleep(1)

    chrome.find_element(by=By.CLASS_NAME, value="css-h629qq").click()
    time.sleep(1)
    chrome.find_element(by=By.CLASS_NAME, value="css-foh633").send_keys("testing")
    chrome.find_element(by=By.CLASS_NAME, value="css-2dhvf4").find_element(by=By.CLASS_NAME, value="css-h629qq").click()
    #重新導向post頁面
    chrome.get('http://127.0.0.1:3000/keystone/posts')
    post_list = chrome.find_element(by=By.CLASS_NAME, value="Table").text
    assert 'testing' in post_list
    chrome.close()

def test_EditPost(): 
    service = Service(r'./chromedriver.exe')
    chrome = webdriver.Chrome(service=service)
    chrome.get("http://127.0.0.1:3000/keystone/signin") 
    chrome.find_element(by=By.NAME, value="email").send_keys('demo@keystonejs.com') 
    chrome.find_element(by=By.NAME, value="password").send_keys('demo') 
    chrome.find_element(by=By.CLASS_NAME, value="css-2960tt").click() 
    time.sleep(1) 

    chrome.get("http://127.0.0.1:3000/keystone/posts") 
    href = chrome.find_elements(by=By.CLASS_NAME, value="ItemList__col")[0].find_element(by=By.CLASS_NAME, value="ItemList__value").get_attribute("href") 
    chrome.get(href) 
    time.sleep(1) 

    chrome.find_element(by=By.ID, value="react-select-2--value").click() 
    time.sleep(1) 

    chrome.find_element(by=By.CLASS_NAME, value="Select-menu-outer").find_element(by=By.ID, value="react-select-2--option-1").click() 
    chrome.find_element(by=By.ID, value="react-select-3--value").click() 
    chrome.find_element(by=By.CLASS_NAME, value="Select-menu-outer").find_element(by=By.ID, value="react-select-3--option-0").click() 
    chrome.find_element(by=By.ID, value="keystone-html-0_ifr").send_keys('testing') 
    chrome.find_element(by=By.ID, value="keystone-html-1_ifr").send_keys('testing') 
    chrome.find_element(by=By.CLASS_NAME, value="css-2960tt").click() 
    time.sleep(1) 
    message = chrome.find_element(by=By.CLASS_NAME, value="css-ctpeu").text 
    assert 'Your changes have been saved successfully'==message 
    chrome.close() 

def test_SearchPost():
    service = Service(r'./chromedriver.exe')
    chrome = webdriver.Chrome(service=service)
    chrome.get("http://127.0.0.1:3000/keystone/signin")
    chrome.find_element(by=By.NAME, value="email").send_keys('demo@keystonejs.com')
    chrome.find_element(by=By.NAME, value="password").send_keys('demo')
    chrome.find_element(by=By.CLASS_NAME, value="css-2960tt").click()
    time.sleep(1)

    chrome.get('http://127.0.0.1:3000/keystone/posts')
    chrome.find_element(by=By.CLASS_NAME, value="css-foh633").send_keys("testing")
    time.sleep(1)
    assert "testing" in chrome.find_element(by=By.CLASS_NAME, value="Table").text
    chrome.close()


    
def test_CreateComment():
    service = Service(r'./chromedriver.exe')
    chrome = webdriver.Chrome(service=service)
    chrome.get("http://127.0.0.1:3000/keystone/signin")
    chrome.find_element(by=By.NAME, value="email").send_keys('demo@keystonejs.com')
    chrome.find_element(by=By.NAME, value="password").send_keys('demo')
    chrome.find_element(by=By.CLASS_NAME, value="css-2960tt").click()
    time.sleep(1)

    chrome.get('http://127.0.0.1:3000/keystone/post-comments')
    chrome.find_element(by=By.CLASS_NAME, value="css-h629qq").click()
    chrome.find_elements(by=By.CLASS_NAME, value="Select-placeholder")[0].click()
    chrome.find_element(by=By.ID, value="react-select-2--option-0").click()
    time.sleep(1)

    chrome.find_elements(by=By.CLASS_NAME, value="Select-placeholder")[0].click()
    chrome.find_element(by=By.ID, value="react-select-3--option-0").click()
    chrome.find_element(by=By.CLASS_NAME, value="css-2dhvf4").find_element(by=By.CLASS_NAME, value="css-h629qq").click()
    chrome.get("http://127.0.0.1:3000/keystone/post-comments")
    table = chrome.find_element(by=By.CLASS_NAME, value="Table").text
    assert 'Demo User' in table
    assert 'testing' in table

    
def test_DeleteComment():
    service = Service(r'./chromedriver.exe')
    chrome = webdriver.Chrome(service=service)
    chrome.get("http://127.0.0.1:3000/keystone/signin")
    chrome.find_element(by=By.NAME, value="email").send_keys('demo@keystonejs.com')
    chrome.find_element(by=By.NAME, value="password").send_keys('demo')
    chrome.find_element(by=By.CLASS_NAME, value="css-2960tt").click()
    time.sleep(1)

    chrome.get('http://127.0.0.1:3000/keystone/post-comments')
    chrome.find_element(by=By.CLASS_NAME, value="ItemList__control--delete").click()
    chrome.find_element(by=By.CLASS_NAME, value="css-t4884").click()
    time.sleep(1)
    message = chrome.find_element(by=By.CLASS_NAME, value="css-pbviij").text
    assert 'No comments found...' == message
    chrome.close()

def test_CreateCategory():
    service = Service(r'./chromedriver.exe')
    chrome = webdriver.Chrome(service=service)
    chrome.get("http://127.0.0.1:3000/keystone/signin")
    chrome.find_element(by=By.NAME, value="email").send_keys('demo@keystonejs.com')
    chrome.find_element(by=By.NAME, value="password").send_keys('demo')
    chrome.find_element(by=By.CLASS_NAME, value="css-2960tt").click()
    time.sleep(1)

    chrome.get('http://127.0.0.1:3000/keystone/post-categories')
    chrome.find_element(by=By.CLASS_NAME, value="css-h629qq").click()
    chrome.find_element(by=By.CLASS_NAME, value="css-foh633").send_keys("testing")
    chrome.find_element(by=By.CLASS_NAME, value="css-2dhvf4").find_element(by=By.CLASS_NAME, value="css-h629qq").click()
    time.sleep(1)

    chrome.get('http://127.0.0.1:3000/keystone/post-categories')
    category_list = chrome.find_element(by=By.CLASS_NAME, value="Table").text
    assert 'testing' in category_list
    chrome.close()

def test_DeleteCategory():
    service = Service(r'./chromedriver.exe')
    chrome = webdriver.Chrome(service=service)
    chrome.get("http://127.0.0.1:3000/keystone/signin")
    chrome.find_element(by=By.NAME, value="email").send_keys('demo@keystonejs.com')
    chrome.find_element(by=By.NAME, value="password").send_keys('demo')
    chrome.find_element(by=By.CLASS_NAME, value="css-2960tt").click()
    time.sleep(1)

    chrome.get('http://127.0.0.1:3000/keystone/post-categories')
    chrome.find_element(by=By.CLASS_NAME, value="ItemList__col--delete").click()
    chrome.find_element(by=By.CLASS_NAME, value="css-t4884").click()
    time.sleep(1)
    message = chrome.find_element(by=By.CLASS_NAME, value="css-pbviij").text
    assert 'No categories found...' == message
    chrome.close()

def test_DeletePost():
    service = Service(r'./chromedriver.exe')
    chrome = webdriver.Chrome(service=service)
    chrome.get("http://127.0.0.1:3000/keystone/signin")
    chrome.find_element(by=By.NAME, value="email").send_keys('demo@keystonejs.com')
    chrome.find_element(by=By.NAME, value="password").send_keys('demo')
    chrome.find_element(by=By.CLASS_NAME, value="css-2960tt").click()
    time.sleep(1)

    chrome.get('http://127.0.0.1:3000/keystone/posts')
    chrome.find_element(by=By.CLASS_NAME, value="ItemList__control--delete").click()
    chrome.find_element(by=By.CLASS_NAME, value="css-t4884").click()
    time.sleep(1)
    message = chrome.find_element(by=By.CLASS_NAME, value="css-pbviij").text
    assert "No posts found..." == message
    chrome.close()