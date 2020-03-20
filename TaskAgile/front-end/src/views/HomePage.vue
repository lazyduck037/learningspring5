<template>
  <div>
    <PageHeader />
    <div class="boards-container">
      <div class="boards-section">
        <h2 class="section-title">{{ $t('homePage.personalBoards') }}</h2>
        <div class="boards d-flex align-content-start flex-wrap">
          <div class="board list-inline-item" v-for="board in personalBoards"
               v-bind:key="board.id" @click="openBoard(board)">
            <h3>{{ board.name }}</h3>
            <p>{{ board.description }}</p>
          </div>
          <div class="board add list-inline-item" @click="createBoard()">
            <font-awesome-icon icon="plus" />
            <div>{{ $t('homePage.createNewBoard') }}</div>
          </div>
        </div>
      </div>
      <div class="boards-section" v-for="team in teamBoards" v-bind:key="team.id">
        <h2 class="section-title">{{ team.name }}</h2>
        <div class="boards d-flex align-content-start flex-wrap">
          <div class="board list-inline-item" v-for="board in team.boards"
               v-bind:key="board.id" @click="openBoard(board)">
            <h3>{{ board.name }}</h3>
            <p>{{ board.description }}</p>
          </div>
          <div class="board add list-inline-item" @click="createBoard(team)">
            <font-awesome-icon icon="plus" />
            <div>{{ $t('homePage.createNewBoard') }}</div>
          </div>
        </div>
      </div>

      <div class="create-team-wrapper">
        <button class="btn btn-link" @click="createTeam()">+ {{ $t('homePage.createNewTeam') }}</button>
      </div>
    </div>
    <CreateBoardModal
      :teamId="selectedTeamId"
      @created="onBoardCreated" />
    <CreateTeamModal />
  </div>
</template>

<script>
import $ from 'jquery'
import PageHeader from '@/components/PageHeader.vue'
import CreateBoardModal from '@/modals/CreateBoardModal.vue'
import CreateTeamModal from '@/modals/CreateTeamModal.vue'
import { mapGetters } from 'vuex'

export default {
  name: 'HomePage',
  components: {
    PageHeader,
    CreateBoardModal,
    CreateTeamModal
  },
  computed: {
    ...mapGetters([
      'personalBoards',
      'teamBoards'
    ])
  },
  methods: {
    openBoard (board) {
      this.$router.push({ name: 'board', params: { boardId: board.id } })
    },
    createBoard (team) {
      this.selectedTeamId = team ? team.id : 0
      $('#createBoardModal').modal('show')
    },
    createTeam () {
      $('#createTeamModal').modal('show')
    },
    onBoardCreated (boardId) {
      this.$router.push({ name: 'board', params: { boardId: boardId } })
    }
  }
}
</script>
