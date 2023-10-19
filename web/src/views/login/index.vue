<template>
  <div class="content">
    <div class="login-wrapper">
      <div class="left-img">
        <div class="glass">
          <div class="tips">
            <div class="title">
              Fast Delivery
            </div>
            <h1>Explore The Efficient</h1>
            <span>Nobody have joined our network.</span>
            <span>we invite you to join the tribe</span>
          </div>
        </div>
      </div>
      <div class="right-login-form">
        <div class="form-wrapper">
          <h1>Log in</h1>
          <div class="input-items">
                        <span class="input-tips">
                            Phone
                        </span>
            <input type="text" v-model="loginForm.phone" class="inputs" placeholder="Enter your Phone">
          </div>
          <div class="input-items">
                        <span class="input-tips">
                            Password
                        </span>
            <input type="password" v-model="loginForm.password" class="inputs" placeholder="Enter password" >
            <span class="forgot">Forgot Password</span>
          </div>
          <button class="btn" @click="login">Log in</button>
          <div class="siginup-tips">
            <span>Don't Have An Account?</span>
            <span>Contract Us</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import {reactive} from "vue";
import userStore from "@/store/modules/user.ts";
import {ElMessage} from "element-plus";
import {useRouter} from "vue-router";

let useUserStore = userStore();
let $router = useRouter();


const loginForm = reactive({
  phone: '13333333333',
  password: '123456'
})

const login = async () => {
  if (loginForm.phone.length != 11) {
    ElMessage({
      type: 'error',
      message: '输入正确的手机号',
    })
    return;
  }
  if (loginForm.password.length == '') {
    ElMessage({
      type: 'error',
      message: '请输入密码',
    })
    return;
  }
  await useUserStore.login(loginForm)
      .then(success => {
        $router.push({
          path: '/home'
        })
      }, error => {
        ElMessage({
          type:'error',
          message: (error as Error).message,
        })
      });
}


</script>

<style scoped lang="scss">
* {
  padding: 0;
  margin: 0;
  font-family: 'Aguazyuan', Courier, monospace;
}

.content {
  width: 100vw;
  height: 100vh;
  background-color: rgb(186, 175, 221);
  position: relative;

  .login-wrapper {
    width: 70vw;
    height: 80vh;
    background-color: #fff;
    border-radius: 40px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    display: flex;

    .left-img {
      border-radius: 40px;
      flex: 1;
      background: url(../../assets/bg.jpg) no-repeat;
      background-size: cover;
      position: relative;

      .glass {
        width: 60%;
        padding: 20px;
        color: #fff;
        position: absolute;
        top: 20%;
        left: 50%;
        transform: translate(-50%, -20%);
        background: rgba(255, 255, 255, 0.1);
        box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);
        backdrop-filter: blur(10px);
        -webkit-backdrop-filter: blur(10px);
        border-radius: 15px;
        border: 1px solid rgba(255, 255, 255, 0.48);

        .tips {
          .title {
            width: 25%;
            font-weight: 600;
            font-size: 15px;
            text-align: center;
            padding: 10px;
            overflow: hidden;
            background: rgba(255, 255, 255, 0.1);
            box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);
            backdrop-filter: blur(10px);
            -webkit-backdrop-filter: blur(10px);
            border-radius: 15px;
            border: 1px solid rgba(255, 255, 255, 0.48);
          }

          h1 {
            margin: 15px 0;
          }

          span {
            margin: 5px 0;
            display: block;
            font-weight: 100;
          }
        }
      }
    }

    .right-login-form {
      flex: 1;
      position: relative;

      .form-wrapper {
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);

        h1 {
          font-size: 30px;
          text-align: center;
        }

        .input-items {
          margin: 20px 0;

          .input-tips {
            display: inline-block;
            font-weight: 600;
            font-size: 20px;
            margin: 10px 0;
          }

          .inputs {
            width: 100%;
            height: 50px;
            border-radius: 10px;
            border: 1px solid rgb(227, 227, 227);
            padding: 20px;
            box-sizing: border-box;
            outline: none;
            transition: .5s;

            &:focus {
              border: 1px solid rgb(128, 106, 196);
            }
          }

          .forgot {
            float: right;
            font-weight: 600;
            text-align: right;
            margin: 5px 0;
            font-size: 13px;
            cursor: pointer;
          }
        }

        .btn {
          width: 100%;
          height: 50px;
          background-color: rgb(128, 106, 196);
          border: 0;
          border-radius: 10px;
          color: #fff;
          font-size: 25px;
          margin: 20px 0;
          cursor: pointer;
        }

        .siginup-tips {
          text-align: center;
          font-weight: 600;
          margin: 10px 0;

          span:last-child {
            color: rgb(110, 87, 179);
            cursor: pointer;
          }
        }
      }
    }
  }
}

@media (max-width: 1024px) {
  .login-wrapper {
    padding: 20px;

    .left-img {
      display: none;
    }

    .right-login-form {
      padding: 20px;
    }
  }
}
</style>