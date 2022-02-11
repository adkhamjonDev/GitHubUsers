package uz.adkhamjon.githubusers.domain.model

data class GitUserModel(
    val id: Int,
    val login: String,
    val avatar_url: String,
    val url: String
)
