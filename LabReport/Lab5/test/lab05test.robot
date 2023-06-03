*** Settings ***
Library           SeleniumLibrary
Documentation    Lab05 RobotFrameWork test
*** Keywords ***
#登入操作
Login
    Open Browser    http://127.0.0.1:3000/keystone/signin    chrome
    Input Text    name=email    	tbj@keystonejs.com
    Input Text    name=password    @123456789
    Click Button    class=css-2960tt   
#切換到post頁面
Post
    Click Link    link=Posts
#切換到comment頁面
Comment
    Click Link    link=Comments
#切換到category頁面
Categories
    Click Link    link=Categories   
#切換到user頁面
Users
    Click Link    link=Users
*** Variables ***
#成功後Your changes have been saved successfully
#after delete post "No posts found..."
#after delete comments "No comments found..."
#after delete category "No categories found..."
${USER}  tbj tbj@keystonejs.com
${SUS_TEXT}    Your changes have been saved successfully
${DEL_POST}    No posts found...
${DEL_COM}    No comments found...
${DEL_CAT}    No categories found...


*** Test Cases ***
test_CreateUser
    Login
	Wait Until Keyword Succeeds	3x	200ms	Users
	Wait Until Keyword Succeeds	3x	200ms	Click Button	class=css-we21er
	Input Text	name=name.first	vrbev
	Input Text	name=name.last	vrf
	Input Text	name=email	ctbff@keystonejs.com
	Input Text	name=password	@12345678
	Wait Until Keyword Succeeds	3x	200ms	Input Text	name=password_confirm	@12345678
	Click Button	class=css-h629qq
	Wait Until Keyword Succeeds	3x	200ms	Click Button	class=css-2960tt
	${result}	Wait Until Keyword Succeeds	3x	200ms	Get Text	class=css-ctpeu
	
    Should Be Equal    ${result}    ${SUS_TEXT}
	Close Browser

test_CreatePost
    Login
	Wait Until Keyword Succeeds	3x	300ms	Post
	Wait Until Keyword Succeeds	3x	300ms	Click Button	class=css-h629qq
	Input Text	class=css-foh633	testing
	Click Button	//button[.//text() = 'Create']
	wait Until Keyword Succeeds	3x	300ms	Click Element	//span[@class="Select-arrow-zone"] 
	wait Until Keyword Succeeds	3x	300ms	Click Element	class=Select-menu-outer
	wait Until Keyword Succeeds	3x	300ms	Click Element	xpath=//*[@id="react-root"]/div/main/div/div/div[1]/form/div[1]/div[1]/div/div[4]/div/div/div/div/span
	wait Until Keyword Succeeds	3x	300ms	Click Element	class=Select-menu-outer
	Select Frame	id=keystone-html-0_ifr
	Input Text	//*[@id="tinymce"]/p	testing
	Unselect Frame
	Select Frame	id=keystone-html-1_ifr
	Input Text	//*[@id="tinymce"]/p	testing
	Unselect Frame
	Click Button	class=css-2960tt
	${result}	Wait Until Keyword Succeeds	3x	200ms	Get Text	class=css-ctpeu
	Should Contain	${result}	${SUS_TEXT}
	Close Browser

test_EditPost
    Login
	Wait Until Keyword Succeeds	3x	300ms	Post
	wait Until Keyword Succeeds	3x	300ms	Click Link	link=testing
	wait Until Keyword Succeeds	3x	300ms	Select Frame	id=keystone-html-0_ifr
	Input Text	//*[@id="tinymce"]/p	testing
	Unselect Frame  #使用 Unselect Frame 返回到最外層。
	Click Button	class=css-2960tt
	${result}	Wait Until Keyword Succeeds	3x	300ms	Get Text	class=css-ctpeu
	Should Contain	${result}	${SUS_TEXT}
	Close Browser
test_SearchPost
    Login
    Wait Until Keyword Succeeds	3x	300ms    Post
    Wait Until Keyword Succeeds	3x	300ms    Input Text    class=css-foh633    testing
    ${result}    Wait Until Keyword Succeeds	3x	300ms    Get Text    xpath=//table[@class='Table ItemList']/tbody/tr/td[2]/a
    Should Contain	${result}	testing
    Close Browser

test_CreateComment
    Login
	Wait Until Keyword Succeeds	3x	300ms	Post
	Wait Until Keyword Succeeds	3x	300ms	Comment
	Click Button	class=css-h629qq
	wait Until Keyword Succeeds	3x	300ms	Click Element	//span[@class="Select-arrow-zone"] 
	wait Until Keyword Succeeds	3x	300ms	Click Element	class=Select-menu-outer
	wait Until Keyword Succeeds	3x	300ms	Click Element	xpath=/html/body/div[2]/div/div/div/div/form/div[2]/div[2]/div/div/div/div/span
	wait Until Keyword Succeeds	3x	300ms	Click Element	class=Select-menu-outer
	Click Button	xpath=/html/body/div[2]/div/div/div/div/form/div[3]/button[1]
	wait Until Keyword Succeeds	3x	300ms	Select Frame	id=keystone-html-0_ifr
	Input Text	//*[@id="tinymce"]/p	testing
	Unselect Frame
	Click Button	class=css-2960tt
	${result}	Wait Until Keyword Succeeds	3x	300ms	Get Text	class=css-ctpeu
	Should Contain	${result}	${SUS_TEXT}
	Close Browser

test_DeleteComment
    Login
	Wait Until Keyword Succeeds	3x	300ms	Post
	Wait Until Keyword Succeeds	3x	300ms	Comment
	Wait Until Keyword Succeeds	3x	300ms    Click Button    class=ItemList__control--delete
	Wait Until Keyword Succeeds	3x	300ms    Click Button    class=css-t4884
	${result}	Wait Until Keyword Succeeds	3x	300ms	Get Text	class=css-pbviij
	Should Be Equal    ${result}    ${DEL_COM}

test_CreateCategory
    Login
	Wait Until Keyword Succeeds	3x	300ms	Post
	Wait Until Keyword Succeeds	3x	300ms	Categories
	Wait Until Keyword Succeeds	3x	300ms    Click Button    class=css-h629qq
    Input Text    class=css-foh633   testing
	Click Button    //button[.//text() = 'Create']
	Wait Until Keyword Succeeds	3x	300ms    Click Button	class=css-2960tt
    ${result}	Wait Until Keyword Succeeds	3x	300ms	Get Text	class=css-ctpeu
	Should Be Equal    ${result}    ${SUS_TEXT}
	Close Browser

test_DeleteCategory
    Login
	Wait Until Keyword Succeeds	3x	300ms	Post
	Wait Until Keyword Succeeds	3x	300ms	Categories
	Wait Until Keyword Succeeds	3x	300ms    Click Button    class=ItemList__control--delete
	Wait Until Keyword Succeeds	3x	300ms    Click Button    class=css-t4884
	${result}	Wait Until Keyword Succeeds	3x	300ms	Get Text	class=css-pbviij
	Should Be Equal    ${result}    ${DEL_CAT}
test_DeletePost
    Login
	Wait Until Keyword Succeeds	3x	300ms	Post
	Wait Until Keyword Succeeds	3x	300ms	Post
	Wait Until Keyword Succeeds	3x	300ms    Click Button    class=ItemList__control--delete
	Wait Until Keyword Succeeds	3x	300ms    Click Button    class=css-t4884
	${result}	Wait Until Keyword Succeeds	3x	300ms	Get Text	class=css-pbviij
	Should Be Equal    ${result}    ${DEL_POST}
	Close Browser