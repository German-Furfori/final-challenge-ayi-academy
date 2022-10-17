export const getUser = (users, userLogged) => {
    return users.find(user => user.username === userLogged.username);
}

export const getIndexUser = (users, userLogged) => {
    return users.findIndex(user => user.username === userLogged.username);
}

export const isUserLogged = (userLogged) => {
    return userLogged != null;
}