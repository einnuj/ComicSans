function getRandomCoverArt() {
    switch(Math.floor(Math.random() * (3))) {
        case 0:
            return [0,"images/covers/CoConutCover.png"];
        case 1:
            return [1,"images/covers/DoenutCover.png"];
        case 2:
            return [2,"images/covers/DoofusCover.png"];
    }
}