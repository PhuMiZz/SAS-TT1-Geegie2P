const { defineConfig } = require("cypress");

module.exports = defineConfig({
  e2e: {
   specPattern: "cypress/e2e/**/*.{cy,spec}.{js,jsx,ts,tsx}",
   baseUrl:"http://intproj22.sit.kmutt.ac.th/tt1",
   experimentalSessionAndOrigin: true
  },
});
