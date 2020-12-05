<template>
  <div>
    <div v-for="(item, index) in content" :key="item.id">
      <header><h2>Потребител №{{index+1}}</h2></header>
      Name: {{item.firstname}} {{item.lastname}}
      <br>
      Number: {{item.num}}
        <div v-if="item.city">
      City: {{item.city.name}}
      </div>
      <hr>
    </div>
  </div>
</template>

<script>
import customerServices from '../services/customer-service'
export default {
  name: 'Customer',
  data () {
    return {
      content: [{
        id: '',
        firstname: '',
        lastname: '',
        num: '',
        city: {
          id: '',
          name: ''
        }
      }]
    }
  },
  mounted () {
    customerServices.getAllCustomers().then(
      response => {
        this.content = response.data
      },
      error => {
        this.content =
          (error.response && error.response.data) ||
          error.message ||
          error.toString()
      }
    )
  }
}
</script>

<style scoped>

</style>
