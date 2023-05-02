<script setup>
import TextDescription from "../UI/molecules/TextDescription.vue";
import AnnouncementCard from "../templates/AnnouncementCard.vue";
</script>

<template>
  <AnnouncementCard>
    <template #title><div class="ann-title text-3xl">title</div></template>
    <template #description>
      <div class="text-[#336699]">Description</div>
      <div class="ann-description">deadskjlkasdg</div>
    </template>
    <template #detail>
      <TextDescription>
        <template #header>Category</template>
        cate
      </TextDescription>

      <TextDescription class="ann-publish-date">
        <template #header>Publish Date</template>
        publish date
      </TextDescription>

      <TextDescription class="ann-close-date">
        <template #header>Close Date</template>
        close date
      </TextDescription>

      <TextDescription class="ann-display">
        <template #header>Display</template>
        Display
      </TextDescription>
    </template>
  </AnnouncementCard>
</template>

<style scoped></style>
