import { mount, createLocalVue } from '@vue/test-utils'
import RegisterPage from '@/views/RegisterPage'
import VueRouter from 'vue-router'
import registrationService from '@/services/registration'

const localVue = createLocalVue()
const router = new VueRouter()

localVue.use(VueRouter)

//mock dependency registrationService
jest.mock('@/services/registration')

describe('RegisterPage.vue',()=> {
  let wrapper
  let fieldUsername
  let fieldEmailAddress
  let fieldPassword
  let buttonSubmit
  let registerSpy

  beforeEach(()=>{
      wrapper = mount(RegisterPage, {
        localVue,
        router
      })
      fieldUsername = wrapper.find('#username')
      fieldEmailAddress = wrapper.find('#emailAddress')
      fieldPassword = wrapper.find('#password')
      buttonSubmit = wrapper.find('form button[type="submit"]')
      registerSpy = jest.spyOn(registrationService, 'register')
    })

  afterAll(()=>{
    jest.restoreAllMocks()
    registerSpy.mockReset()
    registerSpy.mockRestore()
  })

  it ('should render correct contents', () => {
    expect(wrapper.find('.logo').attributes().src).toEqual('/static/images/logo.png')
    expect(wrapper.find('.tagline').text()).toEqual('Open source task management tool')
    expect(fieldUsername.element.value).toEqual('')
    expect(fieldEmailAddress.element.value).toEqual('')
    expect(fieldPassword.element.value).toEqual('')
    expect(buttonSubmit.text()).toEqual('Create account')

  })

  it ('should contain data model with initial values', () => {
    expect(wrapper.vm.form.username).toEqual('')
    expect(wrapper.vm.form.emailAddress).toEqual('')
    expect(wrapper.vm.form.password).toEqual('')
  })

  // it ('should have form inputs bound with data model', () => {
  //   const username = 'sunny'
  //   const emailAddress = 'sunny@local'
  //   const password = 'VueJsRock!'
  //   wrapper.vm.form.username = 'sunny'
  //   wrapper.vm.form.emailAddress = emailAddress
  //   wrapper.vm.form.password = password
  //   expect(fieldUsername.element.value).toEqual(username)
  //   expect(fieldEmailAddress.element.value).toEqual(emailAddress)
  //   expect(fieldPassword.element.value).toEqual(password)
  // })

  it ('should have form submit event handler `submitForm`', () => {
    const stub = jest.fn()
    wrapper.setMethods({submitForm:stub})
    buttonSubmit.trigger('submit')
    expect(stub).toBeCalled()
  })

  it('should register when it is a new user',async ()=> {
    const stub = jest.fn()
    wrapper.vm.$router.push = stub
    wrapper.vm.form.username = 'sunny'
    wrapper.vm.form.emailAddress = 'sunny@local'
    wrapper.vm.form.password = 'Jest!'
    wrapper.vm.submitForm()
    expect(registerSpy).toBeCalled()
    await wrapper.vm.$nextTick()
    expect(stub).toHaveBeenCalledWith({name: 'LoginPage'})

  })

  it('should fail it is not a new user',async ()=>{
    //in the mock, only sunny@local is new user
    wrapper.vm.form.emailAddress = 'ted@local'
    wrapper.vm.submitForm()
    expect(registerSpy).toBeCalled()
    await wrapper.vm.$nextTick()
    expect(wrapper.find('.failed').isVisible()).toBe(true)

  })

})
