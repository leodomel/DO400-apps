beforeEach(() => {
    // GIVEN the user is on the app
    cy.visit("http://sciweb.embasanet.ba.gov.br/sci-web/");
});

describe("add player", () => {
    it("should add a player", () => {
        // AND the user has entered 'bobby' into the 'Player Name' field
        //cy.get("form").find('[placeholder="Player Name"]').type("bobby");
        cy.get('[id^=loginForm-usuario-]').should("be.visible");
        cy.get('[id^=loginForm-usuario-]').type("e367109");
        cy.get('[id^=loginForm-senha-]').type("MONIRAhacker17");

        // WHEN they submit the form
        cy.get("form").submit();

        cy.get('#arvoreSearch').type("RBXA");
        cy.get('#MGER > :nth-child(1)').click();
        cy.get('#RBXA_anchor').should("be.visible");
        cy.get('#RBXA_anchor').click();
        


        const getIframeDocument = () => {
            return 
            //cy.get('iframe[data-cy="the-frame"]')

            cy.get('#frame-content')
            // Cypress yields jQuery element, which has the real
            // DOM element under property "0".
            // From the real DOM iframe element we can get
            // the "document" element, it is stored in "contentDocument" property
            // Cypress "its" command can access deep properties using dot notation
            // https://on.cypress.io/its
            .its('0.contentDocument').should('exist')
          }
          
          const getIframeBody = () => {
            // get the document
            return getIframeDocument()
            // automatically retries until body is loaded
            .its('body').should('not.be.undefined')
            // wraps "body" DOM element to allow
            // chaining more Cypress commands, like ".find(...)"
            .then(cy.wrap)
          }
          
          it('gets the post', () => {
            cy.visit('index.html')
            getIframeBody().find('#run-button').should('have.text', 'Try it').click()
            getIframeBody().find('#result').should('include.text', '"delectus aut autem"')
          });

        // THEN 'bobby' should be added to the players
        //cy.get("#player-scores").should("contain", "bobby");
    });
});





