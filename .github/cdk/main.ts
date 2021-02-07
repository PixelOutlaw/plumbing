import { Construct } from "constructs";
import {App, Stack, StepsProps} from "cdkactions";
import {
  createGradleLibraryPullRequestWorkflow,
  createGradleLibraryPrepareForReleaseWorkflow,
  createGradleLibraryReleaseWorkflow, GradleLibraryConfig
} from "@pixeloutlaw/github-cdkactions";

export class MyStack extends Stack {
  constructor(scope: Construct, id: string) {
    super(scope, id);

    // define workflows here
    const preTestSteps: StepsProps[] = [{
      name: "Run Spigot Build Tools",
      run: "./gradlew runSpigotBuildTools"
    }];
    const gradleLibraryConfig: GradleLibraryConfig = {
      preTestSteps
    };
    createGradleLibraryPullRequestWorkflow(this, gradleLibraryConfig);
    createGradleLibraryPrepareForReleaseWorkflow(this, gradleLibraryConfig);
    createGradleLibraryReleaseWorkflow(this);
  }
}

const app = new App();
new MyStack(app, 'cdk');
app.synth();
