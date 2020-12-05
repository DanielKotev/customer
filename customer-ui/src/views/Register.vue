<template>
  <form @submit="register">
  <div>
    FirstName: <input type="text" name="firstname" placeholder="Enter First Name" v-bind="firstname"> <br><br>
  </div>
    <div>
      LastName: <input type="text" name="lastname" placeholder="Enter Last Name" v-bind= "lastname"> <br><br>
    </div>
    <div>
     Number: <input type="text" name="num" placeholder="Enter Number" v-bind= "number">
      <br>
      <br>
    </div>
    <div>
     City: <input type="text" placeholder="Enter Last Name" v-bind= "city">
    </div>
    <br>
    <hr>
   <button>Submit</button>
  </form>
</template>

<script>
export default {
  name: 'Register',
  data () {
    return {
      error: [],
      firstname: null,
      lastname: null,
      number: null,
      city: null
    }
  }
}

</script>

<style scoped>

</style>
