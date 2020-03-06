module.exports = {
  'login page renders elements': function browser(browser) {
    const loginPage = browser.page.LoginPage()

    loginPage
      .navigate()
      .waitForElementVisible('#app', 10000)
      .assert.visible('@usernameInput')
      .assert.visible('@passwordInput')
      .assert.visible('@submitButton')
      .assert.hidden('@formError')

    browser.end()
  },

  // 'login with email address': function (browser) {
  //   const loginPage = browser.page.LoginPage()
  //   const homePage = browser.page.HomePage()
  //
  //   loginPage
  //     .navigate()
  //     .login('sunny', 'JestRocks!')
  //
  //   browser.pause(2000)
  //   homePage
  //     .navigate()
  //     .expect.element('@pageTitle').text.to.contain('Home Page')
  //
  //   browser.end()
  // }
}
