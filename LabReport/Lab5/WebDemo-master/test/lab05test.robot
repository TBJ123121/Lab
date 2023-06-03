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
    Users

test_CreatePost
test_EditPost
test_SearchPost
test_CreateComment
test_DeleteComment
test_CreateCategory
test_DeleteCategory
test_DeletePost