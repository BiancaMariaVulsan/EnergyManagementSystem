export class LoginUserRequest {
    constructor() { }

    public username: string;
    public password: string;
}

export class SignupUserRequest {
    public email: string;
    public username: string;
    public password: string;
    public role: UserRole;
    public firstName: string;
    public lastName: string;
}

export class LoginUserReply {
    public id: number;
    public email: string;
    public username: string;
    public firstName: string;
    public lastName: string;
    public token: string;
    public role: UserRole;

    constructor() { }
}

export class Client {
    constructor() { 
        this.id = 0
    }
    public id: number;
    public email: string;
    public username: string;
    public password: string;
    public role: UserRole;
    public firstName: string;
    public lastName: string;

    public setFrom(other: Client): void {
        this.id = other.id;
        this.username = other.username;
        this.password = other.password;
        this.firstName = other.firstName;
        this.lastName = other.lastName;
        this.email = other.email;
        this.role = other.role;
    }
}

export class UserRole {
    constructor(id?: number, name?: string) {
        this.id = id;
        this.name = name;
    }
    public id: number;
    public name: string;
}