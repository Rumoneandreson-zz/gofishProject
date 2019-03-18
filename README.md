# gofishProject
Simple Go fish game

Gameplay:
The player will have the opportunity to play Go Fish against the Computer. Each player will aim to get pairs (a pair consists of two cards of the same number eg. two seven’s (7) ) of cards either by asking the other player if a card they currently have is in the other player’s hand. If the card is in the other players hand, the player who asked will collect both cards and add the pair to their personal deck before asking the other player for another card. If the other player did not have the card requested the player must take a card from the top of the deck and place in their hand. If the card (takenfrom the deck) is the same card that the player just requested from the opponent, the player may add the pair of cards to their personal deck and ask the other player for another card. If the player has 2 cards which could make a pair in their hand, they may add this pair to their deck also.


A player’s turn ends when the card they asked for is not in the other player’s hand and they did not draw the same card number from the deck. Whenever a player’s hand is empty, they are allowed to draw four(4) additional cards from the deck until it is empty.

The computer AI should not simply keep requesting the first card in its hand but should choose randomly between the options in its hand.

The player with the most personal sets of pairs (largest deck) at the end of game wins. When a game ends the user should be prompted if they would like to play again.




## Instructions for contributors (Intellij)

1. On Start screen of intellij IDE click "checkout from version control" and select git from the dropdown.
2. Put in the URI for the project and (clicking test is optional) also choose the directory you want the project to be stored locally.
  * Instruction 2 only works if SSH is setup with your github account if not then click login to github and then place the http URI in the clone URI text field.


### To RUN
1. Follow the insructions at this link (https://openjfx.io/openjfx-docs/)
