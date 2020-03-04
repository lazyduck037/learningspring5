import Vue from 'vue'
import Vuelidate from 'vuelidate'
import VueRouter from 'vue-router'
import LoginPage from '@/views/LoginPage'
import {mount} from "@vue/test-utils";
import authenticationService from '@/services/authentication'

const localVue = createLocalVue()
localVue.use(Vuelidate)
localVue.use(VueRouter)
const router = new VueRouter()

jest.mock('@/services/authentication')

describe('LoginPage.vue', () => {
  let wrapper
  let fieldUsername
  let fieldPassword
  let buttonSubmit
  let authenticateSpy

  beforeEach(()=>{
    wrapper = mount(LoginPage,{
      localVue,
      router
    })
    fieldUsername = wrapper.find('#username')
    fieldPassword = wrapper.find('#password')
    buttonSubmit = wrapper.find('form button[type="submit"]')

    authenticateSpy = jest.spyOn(authenticationService, 'authenticate')

  })

  afterEach(()=>{

  })

  it('should render correct contents', () => {
    const Constructor = Vue.extend(LoginPage)
    const vm = new Constructor().$mount()
    expect(vm.$el.querySelector('h1').textContent).toEqual('TaskAgile')
  })
})
