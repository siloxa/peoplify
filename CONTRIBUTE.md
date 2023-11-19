# Contribution

if you want to contribute to the Peoplify project, this contributing guide is helpful.

## Translate Documents In Your Language

If Peoplify's documentation has not been translated into your language, you can do this to help us provide better services to your compatriots.

## start contributing

We use GitFlow for branching model on this project. You must have [git-flow](https://github.com/nvie/gitflow/wiki/Installation) installed on your machine.

To start contributing on Peoplify:
- Fork Peoplify's repo
- Clone your fork
- Start your feature branch by `git flow feature start <feature_name>` command
- Create your changes and commits
- Push your feature branch
- Create pull request

```bash
git clone <your-fork-repo-url>
cd Peoplify
git flow feature start <feature_name>
// Create your changes and commits
git push origin feature/<feature_name>
```
and then make pull request.

### Idea
if you haven't any idea for contributing, you can see [Issues](https://github.com/siloxa/peoplify/issues) or [TODO.md](./TODO.md).

## Documentation
If you are adding/changing a feature, add/change documentation of that feature.

## Release Notes
Write your changes into the [RELEASENOTES.md](./RELEASENOTES.md) file.

For example:

```bash
## next release
- ... (Someone's name)
- ... (Someone's name)
- Fixed bug X (Your name)
- Added X (Your name)
```
